package mies.controllerl;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.view.utill.JSFUtil;
import mies.model.entitiesl.Distgeografica1;
import mies.model.entitiesl.Estudiante;
import mies.model.managerl.ManagerCanton;
import mies.model.managerl.ManagerEstudiantes;
import mies.model.managerl.ManagerParroquia;

@ManagedBean
@SessionScoped
public class ControllerEstudiante {
	private String cedula;
	private String direccion, id_prov, id_cant;
	private String nombre;
	private String apellido;
	private Date fechanac;
	private String genero;
	private String celular;
	private String convencional;
	private String email;
	private String redsocial;
	private String autoidentificacion;
	private String discapacidad;
	private Integer porcentaje = 0;
	private Integer tipo_doc;

	private List<Estudiante> listado;
	private List<Distgeografica1> listadof, listadoi, listadoparroquia;
	@EJB
	private ManagerParroquia managero;
	@EJB
	private ManagerCanton managerf;

	@EJB
	private ManagerEstudiantes manager;

	@PostConstruct
	public void inciar() {

		listado = manager.findallEstudiantes();

	}

	public void findCantonbyProv() {

		listadof = managerf.findCantonbyProv(id_prov);
		id_cant = "0";
		direccion = "0";
	}

	public void findParrbyCanton() {

		listadoparroquia = managero.findParrbyCanton(id_cant);
		direccion = "0";

	}

	public void actionListenerAgregar() {

		try {
			manager.agregarEstudiante(cedula, direccion, nombre.toUpperCase(), apellido.toUpperCase(), fechanac, genero, celular, convencional,
					email, redsocial, autoidentificacion, discapacidad, porcentaje, tipo_doc, true);
			listado = manager.findallEstudiantes();
			JSFUtil.crearMensajeInfo("Estudiante registrado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(String cedula) {
		try {
			manager.eliminarEstudiante(cedula);
			listado = manager.findallEstudiantes();
			JSFUtil.crearMensajeInfo("Estudiante " + cedula + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Estudiante e) {
		tipo_doc = e.getTipo_doc();
		cedula = e.getIdPersona();
		nombre = e.getNombre();
		apellido = e.getApellido();
		fechanac = e.getFechanac();
		genero = e.getGenero();
		celular = e.getCelular();
		convencional = e.getConvencional();
		email = e.getEmail();
		redsocial = e.getRedsocial();
		autoidentificacion = e.getIdentificacion();
		discapacidad = e.getDiscapacidad();
		porcentaje = e.getPorcentajeDisc();
		id_prov = e.getDistgeografica1().getDistgeografica1().getDistgeografica1().getIdDistg1();
		findCantonbyProv();
		id_cant = e.getDistgeografica1().getDistgeografica1().getIdDistg1();
		findParrbyCanton();
		direccion = e.getDistgeografica1().getIdDistg1();// parroquia

	}

	public void actionListenerActualizar() {

		try {
			manager.editarEstudiante(cedula, direccion, nombre.toUpperCase(), apellido.toUpperCase(), fechanac, genero, celular, convencional,
					email, redsocial, autoidentificacion, discapacidad, porcentaje, tipo_doc, true);
			listado = manager.findallEstudiantes();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {

		cedula = "";
		direccion = "0";
		nombre = " ";
		apellido = " ";
		fechanac = null;
		genero = " ";
		celular = " ";
		convencional = " ";
		email = " ";
		redsocial = " ";
		autoidentificacion = " ";
		discapacidad = " ";
		porcentaje = 0;
		id_prov="0";
		id_cant="0";
		tipo_doc=0;
		
		

	}

	public String getCedula() {
		return cedula;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public String getGenero() {
		return genero;
	}

	public String getCelular() {
		return celular;
	}

	public String getConvencional() {
		return convencional;
	}

	public String getEmail() {
		return email;
	}

	public String getRedsocial() {
		return redsocial;
	}

	public String getAutoidentificacion() {
		return autoidentificacion;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public List<Estudiante> getListado() {
		return listado;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setConvencional(String convencional) {
		this.convencional = convencional;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRedsocial(String redsocial) {
		this.redsocial = redsocial;
	}

	public void setAutoidentificacion(String autoidentificacion) {
		this.autoidentificacion = autoidentificacion;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public void setListado(List<Estudiante> listado) {
		this.listado = listado;
	}

	public Integer getTipodoc() {
		return tipo_doc;
	}

	public void setTipodoc(Integer tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public String getId_prov() {
		return id_prov;
	}

	public void setId_prov(String id_prov) {
		this.id_prov = id_prov;
	}

	public String getId_cant() {
		return id_cant;
	}

	public void setId_cant(String id_cant) {
		this.id_cant = id_cant;
	}

	public List<Distgeografica1> getListadof() {
		return listadof;
	}

	public void setListadof(List<Distgeografica1> listadof) {
		this.listadof = listadof;
	}

	public List<Distgeografica1> getListadoi() {
		return listadoi;
	}

	public void setListadoi(List<Distgeografica1> listadoi) {
		this.listadoi = listadoi;
	}

	public List<Distgeografica1> getListadoparroquia() {
		return listadoparroquia;
	}

	public void setListadoparroquia(List<Distgeografica1> listadoparroquia) {
		this.listadoparroquia = listadoparroquia;
	}

	public Integer getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(Integer tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	// getters and setters

}// end class
