package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Contacto;

import mies.model.managerl.ManagerContacto;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerContacto {
	private Integer idContacto;
	private String apellidosContacto;
	private String nombresContacto;
	private String parentesco;
	private String telefonoContacto;
	private String convencional;
	private List<Contacto> listado;

	@EJB
	private ManagerContacto manager;

	@PostConstruct
	public void inciar() {
		listado = manager.findallContactos();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarContacto(nombresContacto.toUpperCase(), apellidosContacto.toUpperCase(), parentesco, telefonoContacto, convencional,
					true);
			listado = manager.findallContactos();
			JSFUtil.crearMensajeInfo("Contacto registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	/*
	 * un contacto va como argumento para localizar los noambres y apellidos de
	 * ese contacto
	 */
	public void actionListenerEliminar(Integer idcontacto, Contacto c) {
		try {
			manager.eliminarContacto(idcontacto);
			listado = manager.findallContactos();
			JSFUtil.crearMensajeInfo("Contacto " + idcontacto + c.getNombre() + " " + c.getApellido()
					+ " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Contacto c) {

		idContacto = c.getIdPersona();
		nombresContacto = c.getNombre();
		apellidosContacto = c.getApellido();
		parentesco = c.getParentesco();
		telefonoContacto = c.getCelular();
		convencional = c.getConvencional();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarContacto(idContacto, nombresContacto.toUpperCase(), apellidosContacto.toUpperCase(), telefonoContacto,convencional,parentesco,true);
			listado = manager.findallContactos();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		apellidosContacto = "";
		nombresContacto = "";
		parentesco = "";
		telefonoContacto = "";
		convencional="";

	}

	// getters and setters

	public String getApellidosContacto() {
		return apellidosContacto;
	}

	public void setApellidosContacto(String apellidosContacto) {
		this.apellidosContacto = apellidosContacto;
	}

	public String getNombresContacto() {
		return nombresContacto;
	}

	public void setNombresContacto(String nombresContacto) {
		this.nombresContacto = nombresContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public List<Contacto> getListado() {
		return listado;
	}

	public void setListado(List<Contacto> listado) {
		this.listado = listado;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public String getConvencional() {
		return convencional;
	}

	public void setConvencional(String convencional) {
		this.convencional = convencional;
	}

}// end class
