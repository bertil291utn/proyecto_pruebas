package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Distvinculacion;
import mies.model.managerl.ManagerMinisterio;
import mies.model.managerl.ManagerModalidad;
import mies.model.managerl.ManagerServicio;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerModalidad {
	private int id_ministerio;
	private int id_serv;
	private String nombreMod;
	private int id_mod;
	private List<Distvinculacion> listadom;
	private List<Distvinculacion> listados;
	private List<Distvinculacion> listado;
	private List<Distvinculacion> listadomodalidad;

	@EJB
	private ManagerMinisterio managerm;
	@EJB
	private ManagerServicio managers;
	@EJB
	private ManagerModalidad manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallModalidades();
		listadom = managerm.findallMinisterio();

		// managerf = new ManagerFacultad();
		// listadof = new ArrayList<Facultad>();
	}

	public void findServbyMin() {
		listados = managers.findServbyMin(id_ministerio);

	}

	public void findModbyServ() {
		listadomodalidad = manager.findModalidadbyServ(id_serv);

	}

	public void actionListenerAgregar() {

		try {
			manager.agregarModalidad(id_serv, nombreMod.toUpperCase(), true);
			listado = manager.findallModalidades();
			JSFUtil.crearMensajeInfo("Modalidad registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_mod, Distvinculacion m) {
		try {
			manager.eliminarModalidad(id_mod);
			listado = manager.findallModalidades();
			JSFUtil.crearMensajeInfo("Modalidad\n " + m.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distvinculacion m) {
		id_mod = m.getIdDistvinculacion();
		id_serv = m.getDistvinculacion().getIdDistvinculacion();
		// Servicio f = managers.findServicio(id_serv);
		// id_ministerio = f.getMinisterio().getIdMin();
		id_ministerio = m.getDistvinculacion().getDistvinculacion().getIdDistvinculacion();
		listados = managers.findServbyMin(id_ministerio);
		nombreMod = m.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarModalidad(id_mod, id_serv, nombreMod.toUpperCase());
			listado = manager.findallModalidades();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombreMod = "";
		id_ministerio = 0;
		id_serv = 0;

	}

	public int getId_ministerio() {
		return id_ministerio;
	}

	public void setId_ministerio(int id_ministerio) {
		this.id_ministerio = id_ministerio;
	}

	public int getId_serv() {
		return id_serv;
	}

	public void setId_serv(int id_serv) {
		this.id_serv = id_serv;
	}

	public int getId_mod() {
		return id_mod;
	}

	public void setId_mod(int id_mod) {
		this.id_mod = id_mod;
	}

	public List<Distvinculacion> getListadom() {
		return listadom;
	}

	public void setListadom(List<Distvinculacion> listadom) {
		this.listadom = listadom;
	}

	public List<Distvinculacion> getListados() {
		return listados;
	}

	public void setListados(List<Distvinculacion> listados) {
		this.listados = listados;
	}

	public List<Distvinculacion> getListado() {
		return listado;
	}

	public void setListado(List<Distvinculacion> listado) {
		this.listado = listado;
	}

	public String getNombreMod() {
		return nombreMod;
	}

	public void setNombreMod(String nombreMod) {
		this.nombreMod = nombreMod;
	}

	public List<Distvinculacion> getListadomodalidad() {
		return listadomodalidad;
	}

	public void setListadomodalidad(List<Distvinculacion> listadomodalidad) {
		this.listadomodalidad = listadomodalidad;
	}

}// end class
