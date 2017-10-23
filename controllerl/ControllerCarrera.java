package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distacademica;
import mies.model.managerl.ManagerCarrera;
import mies.model.managerl.ManagerFacultad;
import mies.model.managerl.ManagerInstituto;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerCarrera {
	private Integer id_institucion, idCarrera, id_facultad;
	private String nombreCarrera;
	private List<Distacademica> listado, listadof, listadoi, listadocarrera;

	@EJB
	private ManagerCarrera manager;
	@EJB
	private ManagerFacultad managerf;
	@EJB
	private ManagerInstituto manageri;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallCarreras();
		listadoi = manageri.findallInstitucion();
	}

	public void findFacubyInst() {
		listadof = managerf.findFacubyInst(id_institucion);

	}

	public void findCarrerabyFacu() {
		listadocarrera = manager.findCarrerabyFacu(id_facultad);

	}

	public void actionListenerAgregar() {

		try {
			manager.agregarCarrera(id_facultad, nombreCarrera.toUpperCase(), true);
			listado = manager.findallCarreras();
			JSFUtil.crearMensajeInfo("Facultad registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_carrera, Distacademica c) {
		try {
			manager.eliminarCarrera(id_carrera);
			listado = manager.findallCarreras();
			JSFUtil.crearMensajeInfo("Carrera\n " + c.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distacademica c) {
		idCarrera = c.getIdDistacademica();
		id_facultad = c.getDistacademica().getIdDistacademica();
		id_institucion = c.getDistacademica().getDistacademica().getIdDistacademica();
		listadof = managerf.findFacubyInst(id_institucion);
		nombreCarrera = c.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarCarrera(idCarrera, id_facultad, nombreCarrera.toUpperCase());
			listado = manager.findallCarreras();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		nombreCarrera = "";
		id_facultad=0;
		id_institucion=0;

	}

	
	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public List<Distacademica> getListado() {
		return listado;
	}

	public void setListado(List<Distacademica> listado) {
		this.listado = listado;
	}

	public List<Distacademica> getListadof() {
		return listadof;
	}

	public void setListadof(List<Distacademica> listadof) {
		this.listadof = listadof;
	}

	

	public List<Distacademica> getListadoi() {
		return listadoi;
	}

	public void setListadoi(List<Distacademica> listadoi) {
		this.listadoi = listadoi;
	}

	public List<Distacademica> getListadocarrera() {
		return listadocarrera;
	}

	public void setListadocarrera(List<Distacademica> listadocarrera) {
		this.listadocarrera = listadocarrera;
	}

	public Integer getId_institucion() {
		return id_institucion;
	}

	public void setId_institucion(Integer id_institucion) {
		this.id_institucion = id_institucion;
	}

	public Integer getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}

	public Integer getId_facultad() {
		return id_facultad;
	}

	public void setId_facultad(Integer id_facultad) {
		this.id_facultad = id_facultad;
	}

	

	
	
}// end class
