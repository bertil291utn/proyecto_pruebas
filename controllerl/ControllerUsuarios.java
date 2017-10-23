package mies.controllerl;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import mies.view.utill.JSFUtil;
import mies.model.entitiesl.Distrito;
import mies.model.entitiesl.Usuario;
import mies.model.managerl.ManagerDistrito;
import mies.model.managerl.ManagerUsuarios;
import mies.model.utill.ModelUtil;

@ManagedBean
@SessionScoped
public class ControllerUsuarios {

	private String cedulaUser;
	private String apellidoUser;
	private String clave;
	private String confimacionclave;
	private String nombreUser;
	private Integer id_dist;
	private boolean confirmaLogin;
	private Usuario user;
	private List<Usuario> listadouser;
	private List<Distrito> listadodistrito;
	private String tipo_User;

	@EJB
	private ManagerUsuarios manager;
	@EJB
	private ManagerDistrito managerd;

	@PostConstruct
	public void iniciar() {
		listadouser = manager.findallusers();
		listadodistrito = managerd.findallDistritos();
		limpiar();

	}

	public String actionLogin() {
		try {
			confirmaLogin = manager.comprobarUsuario(cedulaUser, clave);
			JSFUtil.crearMensajeInfo("Login correcto");
			user = manager.findUsuarioById(cedulaUser);
			if (user.getTipo().equals("A")) {
				cedulaUser = "";
				return "admin/usuario";
			}
			return "ficha/indexficha.xhtml?faces-redirect=true";

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		cedulaUser = "";
		clave = "";
		return "";
		
	}

	public String actionRegistrarUser() {

		try {
			manager.registrarNuevoUser(cedulaUser,nombreUser.toUpperCase(), apellidoUser.toUpperCase(), clave, confimacionclave);
			listadouser = manager.findallusers();
			JSFUtil.crearMensajeInfo("Nuevo usuario registrado.");
			return "admin/usuario.xhtml?faces-redirect=true";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";

	}

	public String actionSalir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		cedulaUser = "";
		clave = "";
		return "login";

	}

	public void actionComprobarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			String path = ec.getRequestPathInfo();
			if (path.equals("/login.xhtml"))
				return;
			if (ModelUtil.isEmpty(cedulaUser))
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			if (!confirmaLogin) {
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				// si hizo login, verificamos que acceda a paginas permitidas:
				if (user.getTipo().equals("A")) {
					if (!path.contains("/admin/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				} // caso contrario es un blogger:
				if (!path.contains("/ficha/"))
					ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String actionListenerEliminar(String cedula) {
		try {
			manager.eliminarUser(cedula);
			listadouser = manager.findallusers();
			JSFUtil.crearMensajeInfo("Usuario " + cedula + " eliminado.");
			return "admin/usuario";
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

	public void actoionListenerCargar(Usuario u) {
		cedulaUser = u.getIdUsuario();
		
		nombreUser = u.getNombre();
		apellidoUser = u.getApellido();
		clave = u.getClave();
		tipo_User = u.getTipo();

	}

	public String actionListenerActualizar() {

		try {
			manager.editarUsuario(cedulaUser,nombreUser.toUpperCase(), apellidoUser.toUpperCase(), tipo_User);
			listadouser = manager.findallusers();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			return "admin/usuario";

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

		return "";

	}

	public void limpiar() {

		cedulaUser = "";
		
		nombreUser = "";
		apellidoUser = "";

	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public String gotoindex() {
		return "indexficha.xhtml?faces-redirect=true";
	}

	public String gotoestudiante() {
		return "estudiante.xhtml?faces-redirect=true";
	}

	/** getters and setters */
	public String getCedulaUser() {
		return cedulaUser;
	}

	public void setCedulaUser(String cedulaUser) {
		this.cedulaUser = cedulaUser;
	}

	public String getApellidoUser() {
		return apellidoUser;
	}

	public void setApellidoUser(String apellidoUser) {
		this.apellidoUser = apellidoUser;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getConfimacionclave() {
		return confimacionclave;
	}

	public void setConfimacionclave(String confimacionclave) {
		this.confimacionclave = confimacionclave;
	}

	public String getNombreUser() {
		return nombreUser;
	}

	public void setNombreUser(String nombreUser) {
		this.nombreUser = nombreUser;
	}

	

	public boolean isConfirmaLogin() {
		return confirmaLogin;
	}

	public void setConfirmaLogin(boolean confirmaLogin) {
		this.confirmaLogin = confirmaLogin;
	}

	public List<Usuario> getListadouser() {
		return listadouser;
	}

	public void setListadouser(List<Usuario> listadouser) {
		this.listadouser = listadouser;
	}

	public String getTipo_User() {
		return tipo_User;
	}

	public void setTipo_User(String tipo_User) {
		this.tipo_User = tipo_User;
	}

	public List<Distrito> getListadodistrito() {
		return listadodistrito;
	}

	public void setListadodistrito(List<Distrito> listadodistrito) {
		this.listadodistrito = listadodistrito;
	}

	public Integer getId_dist() {
		return id_dist;
	}

	public void setId_dist(Integer id_dist) {
		this.id_dist = id_dist;
	}
	
	

}// end class
