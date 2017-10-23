package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distrito;
import mies.model.managerl.ManagerDistrito;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerDistrito {
	private int id_Distrito;
	private String nombreDistrito;
	private List<Distrito> listado;
	private List<Distrito> listadoless;

	@EJB
	private ManagerDistrito manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallDistritos();
		
		//listadoless = manager.findDistritosless();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarDistrito(nombreDistrito.toUpperCase());
			listado = manager.findallDistritos();
			JSFUtil.crearMensajeInfo("Distrito registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_Distrito, Distrito a) {
		try {
			manager.eliminarDistrito(id_Distrito);
			listado = manager.findallDistritos();
			JSFUtil.crearMensajeInfo("Distrito\n " + a.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distrito c) {
		id_Distrito = c.getIdDist();
		nombreDistrito = c.getDescripcion();

	}

	public void actionListenerActualizar() {

		try {
			manager.editarDistrito(id_Distrito, nombreDistrito.toUpperCase());
			listado = manager.findallDistritos();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombreDistrito = "";

	}

	public int getId_Distrito() {
		return id_Distrito;
	}

	public void setId_Distrito(int id_Distrito) {
		this.id_Distrito = id_Distrito;
	}

	public String getDescripcionDistrito() {
		return nombreDistrito;
	}

	public void setDescripcionDistrito(String descripcionDistrito) {
		this.nombreDistrito = descripcionDistrito;
	}

	public List<Distrito> getListado() {
		return listado;
	}

	public void setListado(List<Distrito> listado) {
		this.listado = listado;
	}

	public List<Distrito> getListadoless() {
		return listadoless;
	}

	public void setListadoless(List<Distrito> listadoless) {
		this.listadoless = listadoless;
	}

}// end class
