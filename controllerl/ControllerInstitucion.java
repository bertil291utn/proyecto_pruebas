package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distacademica;
import mies.model.managerl.ManagerInstituto;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped

public class ControllerInstitucion {
	private int id;
	private String nombre;
	private List<Distacademica> listado;

	@EJB
	private ManagerInstituto manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallInstitucion();
	}

	public void actionListenerAgregar() {
		
		try {
			manager.agregarInstitucion(nombre.toUpperCase());
			listado = manager.findallInstitucion();
			JSFUtil.crearMensajeInfo("Instituto registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id, Distacademica i) {
		try {
			manager.eliminarInstituto(id);
			listado = manager.findallInstitucion();
			JSFUtil.crearMensajeInfo("Institucion\n " + i.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distacademica i) {
		id = i.getIdDistacademica();
		nombre = i.getDescripcion();
		
	}

	public void actionListenerActualizar() {

		try {
			manager.editarInstitucion(id, nombre.toUpperCase());
			listado = manager.findallInstitucion();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombre = "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Distacademica> getListado() {
		return listado;
	}

	public void setListado(List<Distacademica> listado) {
		this.listado = listado;
	}

}// end class
