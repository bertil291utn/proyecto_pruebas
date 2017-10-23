package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Estado;
import mies.model.managerl.ManagerEstado;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerEstado {
	private int id_estado;
	private String descripcion;
	private List<Estado> listado;
	private List<Estado> listadodis;

	@EJB
	private ManagerEstado manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallEstado();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarEstado(descripcion.toUpperCase());
			listado = manager.findallEstado();
			JSFUtil.crearMensajeInfo("Estado registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_estado, Estado m) {
		try {
			manager.eliminarEstado(id_estado);
			listado = manager.findallEstado();
			JSFUtil.crearMensajeInfo("Estado \n " + m.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public void actionListenerCargar(Estado i) {
		id_estado = i.getIdEstado();
		descripcion = i.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarEstado(id_estado, descripcion.toUpperCase());
			listado = manager.findallEstado();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {

		descripcion = "";

	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Estado> getListado() {
		return listado;
	}

	public void setListado(List<Estado> listado) {
		this.listado = listado;
	}

	public List<Estado> getListadodis() {
		return listadodis;
	}

	public void setListadodis(List<Estado> listadodis) {
		this.listadodis = listadodis;
	}

}// end class
