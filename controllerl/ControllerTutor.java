package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Tutor;
import mies.model.managerl.ManagerTutor;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerTutor {
	private Integer cedula;
	private String apellido;
	private String celular;
	private String convencional;
	private String direccion;
	private String email;
	private String nombre;
	private List<Tutor> listado;

	@EJB
	private ManagerTutor manager;

	@PostConstruct
	public void iniciar() {

		listado = manager.findallTutores();
	}

	public void actionListenerAgregar() {
		
		try {
			manager.agregarTutor(nombre.toUpperCase(), apellido.toUpperCase(), celular, convencional, email, true);
			listado = manager.findallTutores();
			JSFUtil.crearMensajeInfo("Tutor registrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();
	}

	public void actionListenerEliminar(Integer cedula) {
		try {
			manager.eliminarTutor(cedula);
			listado = manager.findallTutores();
			JSFUtil.crearMensajeInfo("Tutor " + cedula + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Tutor t) {
		cedula = t.getIdPersona();
		nombre = t.getNombre();
		apellido = t.getApellido();
		celular = t.getCelular();
		convencional = t.getConvencional();
		email = t.getEmail();

	}

	public void actionListenerActualizar() {

		try {
			manager.editarTutor(cedula, nombre.toUpperCase(), apellido.toUpperCase(), celular, email, convencional,true);
			listado = manager.findallTutores();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		cedula = 0;
		nombre = "";
		apellido = "";
		celular = "";
		email = "";
		convencional = "";

	}

	// getters and setters
	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Tutor> getListado() {
		return listado;
	}

	public void setListado(List<Tutor> listado) {
		this.listado = listado;
	}

	public String getConvencional() {
		return convencional;
	}

	public void setConvencional(String convencional) {
		this.convencional = convencional;
	}

}// end class
