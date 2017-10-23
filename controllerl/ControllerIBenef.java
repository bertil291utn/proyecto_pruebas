package mies.controllerl;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Ibenef;

import mies.model.managerl.ManagerIbenef;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerIBenef {

	private int id_institucion;
	private String nombreIBenef;
	private int hombres;
	private int mujeres;
	private List<Ibenef> listado;

	@EJB
	private ManagerIbenef manager;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallIbenefs();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarIbenef(nombreIBenef.toUpperCase(), hombres, mujeres);
			listado = manager.findallIbenefs();
			JSFUtil.crearMensajeInfo("Institucion registrada");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_institucion, Ibenef m) {
		try {
			manager.eliminarIbenef(id_institucion);
			listado = manager.findallIbenefs();
			JSFUtil.crearMensajeInfo("Institucion\n " + m.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Ibenef i) {
		id_institucion = i.getIdBenef();
		nombreIBenef = i.getDescripcion();
		hombres = i.getNhombres();
		mujeres = i.getNmujeres();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarIbenef(id_institucion, nombreIBenef.toUpperCase(), hombres, mujeres);
			listado = manager.findallIbenefs();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {

		nombreIBenef = "";
		hombres = 0;
		mujeres = 0;

	}

	public int getId_institucion() {
		return id_institucion;
	}

	public void setId_institucion(int id_institucion) {
		this.id_institucion = id_institucion;
	}

	public String getNombre() {
		return nombreIBenef;
	}

	public void setNombre(String nombre) {
		this.nombreIBenef = nombre;
	}

	public int getHombres() {
		return hombres;
	}

	public void setHombres(int hombres) {
		this.hombres = hombres;
	}

	public int getMujeres() {
		return mujeres;
	}

	public void setMujeres(int mujeres) {
		this.mujeres = mujeres;
	}

	public List<Ibenef> getListado() {
		return listado;
	}

	public void setListado(List<Ibenef> listado) {
		this.listado = listado;
	}

	public ManagerIbenef getManager() {
		return manager;
	}

	public void setManager(ManagerIbenef manager) {
		this.manager = manager;
	}

}// end class
