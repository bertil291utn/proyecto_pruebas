package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distgeografica1;
import mies.model.managerl.ManagerParroquia;
import mies.model.managerl.ManagerCanton;
import mies.model.managerl.ManagerProvincia;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerParroquia {
	private String id_provincia, id_parr, id_canton;
	private String nombreParroquia;
	private List<Distgeografica1> listado, listadof, listadoi, listadoparroquia;

	@EJB
	private ManagerParroquia manager;
	@EJB
	private ManagerCanton managerf;
	@EJB
	private ManagerProvincia manageri;

	private ControllerEstudiante ce;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallParroquia();
		listadoi = manageri.findallProvincia();
		// combosudpate();
	}

	public void combosudpate() {
		if (ce.getId_prov() != null || !ce.getId_prov().isEmpty()) {
			listadof = managerf.findCantonbyProv(ce.getId_prov());
			System.out.println("provincia: " + ce.getId_prov());
		}
		if (ce.getId_cant() != null || !ce.getId_cant().isEmpty()) {
			listadoparroquia = manager.findParrbyCanton(ce.getId_cant());
			System.out.println("provincia: " + ce.getId_prov());
		}

	}

	public void findCantonbyProv() {

		listadof = managerf.findCantonbyProv(id_provincia);

	}

	public void findParrbyCanton() {

		listadoparroquia = manager.findParrbyCanton(id_canton);

	}

	public void actionListenerAgregar() {

		try {
			manager.agregarParroquia(id_parr, id_canton, nombreParroquia.toUpperCase(), true);
			listado = manager.findallParroquia();
			JSFUtil.crearMensajeInfo("Parroquia registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(String id_carrera, Distgeografica1 c) {
		try {
			manager.eliminarParroquia(id_carrera);
			listado = manager.findallParroquia();
			JSFUtil.crearMensajeInfo("Parroquia\n " + c.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distgeografica1 c) {
		id_parr = c.getIdDistg1();
		id_canton = c.getDistgeografica1().getIdDistg1();
		// Facultad f = managerf.findFacultad(id_facultad);
		// id_institucion = f.getInstitucion().getIdInst();
		id_provincia = c.getDistgeografica1().getDistgeografica1().getIdDistg1();
		listadof = managerf.findCantonbyProv(id_provincia);
		nombreParroquia = c.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarParroquia(id_parr, id_canton, nombreParroquia.toUpperCase());
			listado = manager.findallParroquia();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombreParroquia = "";
		id_canton = "";
		id_provincia = "";
		id_parr = "";

	}

	public String getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(String id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getId_parr() {
		return id_parr;
	}

	public void setId_parr(String id_parr) {
		this.id_parr = id_parr;
	}

	public String getId_canton() {
		return id_canton;
	}

	public void setId_canton(String id_canton) {
		this.id_canton = id_canton;
	}

	public String getNombreParroquia() {
		return nombreParroquia;
	}

	public void setNombreParroquia(String nombreParroquia) {
		this.nombreParroquia = nombreParroquia;
	}

	public List<Distgeografica1> getListado() {
		return listado;
	}

	public void setListado(List<Distgeografica1> listado) {
		this.listado = listado;
	}

	public List<Distgeografica1> getListadof() {
		return listadof;
	}

	public void setListadof(List<Distgeografica1> listadof) {
		this.listadof = listadof;
	}

	public List<Distgeografica1> getListadoi() {
		return listadoi;
	}

	public void setListadoi(List<Distgeografica1> listadoi) {
		this.listadoi = listadoi;
	}

	public List<Distgeografica1> getListadoparroquia() {
		return listadoparroquia;
	}

	public void setListadoparroquia(List<Distgeografica1> listadoparroquia) {
		this.listadoparroquia = listadoparroquia;
	}

}// end class
