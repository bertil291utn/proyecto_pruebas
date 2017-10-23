package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distgeografica1;
import mies.model.managerl.ManagerProvincia;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerProvincia {
	private String id;
	private String nombre;
	private List<Distgeografica1> listado;

	@EJB
	private ManagerProvincia manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallProvincia();
	}

	public void actionListenerAgregar() {
		
		try {
			manager.agregarProvincia(id,nombre.toUpperCase());
			listado = manager.findallProvincia();
			JSFUtil.crearMensajeInfo("Provincia registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(String id, Distgeografica1 i) {
		try {
			manager.eliminarProvincia(id);
			listado = manager.findallProvincia();
			JSFUtil.crearMensajeInfo("Provincia\n " + i.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distgeografica1 i) {
		id = i.getIdDistg1();
		nombre = i.getDescripcion();
		
	}

	public void actionListenerActualizar() {

		try {
			manager.editarProvincia(id, nombre.toUpperCase());
			listado = manager.findallProvincia();
			JSFUtil.crearMensajeInfo("Actualización correcta");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		id="";
		nombre = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	

}// end class
