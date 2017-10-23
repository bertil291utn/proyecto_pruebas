package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Distvinculacion;
import mies.model.managerl.ManagerMinisterio;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerMinisterio {
	private int id_min;
	private String nombreMin;
	private List<Distvinculacion> listado;

	@EJB
	private ManagerMinisterio manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallMinisterio();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarMinisterio(nombreMin.toUpperCase());
			listado = manager.findallMinisterio();
			JSFUtil.crearMensajeInfo("Ministerio registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_min, Distvinculacion m) {
		try {
			manager.eliminarMinisterio(id_min);
			listado = manager.findallMinisterio();
			JSFUtil.crearMensajeInfo("Ministerio\n " + m.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distvinculacion i) {
		id_min = i.getIdDistvinculacion();
		nombreMin = i.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarMinisterio(id_min, nombreMin.toUpperCase());
			listado = manager.findallMinisterio();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {

		nombreMin = "";

	}

	public int getId_min() {
		return id_min;
	}

	public void setId_min(int id_min) {
		this.id_min = id_min;
	}

	public String getNombreMin() {
		return nombreMin;
	}

	public void setNombreMin(String nombreMin) {
		this.nombreMin = nombreMin;
	}

	public List<Distvinculacion> getListado() {
		return listado;
	}

	public void setListado(List<Distvinculacion> listado) {
		this.listado = listado;
	}

}// end class
