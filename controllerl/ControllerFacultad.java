package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distacademica;
import mies.model.managerl.ManagerFacultad;
import mies.model.managerl.ManagerInstituto;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerFacultad {
	private int id_facultad;
	private int id_institucion;
	private String id_institucionS;
	private String nombre;
	private List<Distacademica> listado;
	private List<Distacademica> listadoi;

	@EJB
	private ManagerFacultad manager;
	@EJB
	private ManagerInstituto manageri;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallFacultades();
		listadoi = manageri.findallInstitucion();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarFacultad(id_institucion, nombre.toUpperCase());
			listado = manager.findallFacultades();
			JSFUtil.crearMensajeInfo("Facultad registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_facultad, Distacademica f) {
		try {
			manager.eliminarFacultad(id_facultad);
			listado = manager.findallFacultades();
			JSFUtil.crearMensajeInfo("Facultad\n " + f.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distacademica f) {
		id_facultad = f.getIdDistacademica();
		id_institucion = f.getDistacademica().getIdDistacademica();
		id_institucionS = f.getDistacademica().getDescripcion();
		nombre = f.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarFacultad(id_facultad, id_institucion, nombre.toUpperCase());
			listado = manager.findallFacultades();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		id_institucionS = "";
		id_institucion = 0;
		nombre = "";

	}

	public int getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(int id_facultad) {
		this.id_facultad = id_facultad;
	}

	public int getId_institucion() {
		return id_institucion;
	}

	public void setId_institucion(int id_institucion) {
		this.id_institucion = id_institucion;
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

	public List<Distacademica> getListado() {
		return listado;
	}

	public void setListado(List<Distacademica> listado) {
		this.listado = listado;
	}

	public List<Distacademica> getListadoi() {
		return listadoi;
	}

	public void setListadoi(List<Distacademica> listadoi) {
		this.listadoi = listadoi;
	}
	

}// end class
