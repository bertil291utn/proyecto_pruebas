package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Distgeografica1;
import mies.model.managerl.ManagerProvincia;
import mies.model.managerl.ManagerCanton;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerCanton {
	private String id_canton;
	private String id_provincia;
	private String id_institucionS;
	private String nombre;
	private List<Distgeografica1> listado;
	private List<Distgeografica1> listadoi;
//cambiado editado
//otra vez ;lo he cambiado
	
	//ahora lo hemos cambiado desde web por que estaba mal 
	@EJB
	private ManagerCanton manager;
	@EJB
	private ManagerProvincia manageri;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallCanton();
		listadoi = manageri.findallProvincia();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarCanton(id_canton, id_provincia,
					nombre.toUpperCase());
			listado = manager.findallCanton();
			JSFUtil.crearMensajeInfo("Canton registrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}
//no quiero alterar mi codigo pero esto es una prueba desde local
	//commit desde la web
//editado para ver por consola
	public void actionListenerEliminar(String id_canton, Distgeografica1 f) {
		try {
			manager.eliminarCanton(id_canton);
			listado = manager.findallCanton();
			JSFUtil.crearMensajeInfo("Canton\n " + f.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distgeografica1 f) {
		id_canton = f.getIdDistg1();
		id_provincia = f.getDistgeografica1().getIdDistg1();
		id_institucionS = f.getDistgeografica1().getDescripcion();
		nombre = f.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarCanton(id_canton, id_provincia, nombre.toUpperCase());
			listado = manager.findallCanton();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		id_canton = "";
		id_provincia = "";
		nombre = "";

	}

	public String getId_canton() {
		return id_canton;
	}

	public void setId_canton(String id_canton) {
		this.id_canton = id_canton;
	}

	public String getId_provincia() {
		return id_provincia;
	}

	public void setId_provincia(String id_provincia) {
		this.id_provincia = id_provincia;
	}

	public String getId_institucionS() {
		return id_institucionS;
	}

	public void setId_institucionS(String id_institucionS) {
		this.id_institucionS = id_institucionS;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Distgeografica1> getListado() {
		return listado;
	}

	public void setListado(List<Distgeografica1> listado) {
		this.listado = listado;
	}

	public List<Distgeografica1> getListadoi() {
		return listadoi;
	}

	public void setListadoi(List<Distgeografica1> listadoi) {
		this.listadoi = listadoi;
	}

}// end class
