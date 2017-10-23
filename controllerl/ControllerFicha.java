package mies.controllerl;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import mies.controllerl.ControllerUsuarios;
import mies.model.entitiesl.Contacto;
import mies.model.entitiesl.Distacademica;
import mies.model.entitiesl.Distgeografica1;
import mies.model.entitiesl.Distvinculacion;
import mies.model.entitiesl.Estudiante;
import mies.model.entitiesl.Ficha;
import mies.model.entitiesl.Tutor;
import mies.model.managerl.ManagerCanton;
import mies.model.managerl.ManagerCarrera;
import mies.model.managerl.ManagerContacto;
import mies.model.managerl.ManagerEstudiantes;
import mies.model.managerl.ManagerFICHA;
import mies.model.managerl.ManagerFacultad;
import mies.model.managerl.ManagerIbenef;
import mies.model.managerl.ManagerModalidad;
import mies.model.managerl.ManagerParroquia;
import mies.model.managerl.ManagerServicio;
import mies.model.managerl.ManagerTutor;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerFicha {
	private Integer id_ficha;
	private Integer id_ibenef;
	private String nombre_estudiante;
	private String apellido_estudiante;
	private String cedula_e;
	private String celular_e;
	private String convencional_e;
	private String autoidentificacion;
	private String genero;
	private String discapacidad;
	private Integer porcentaje;
	private Date fechanac;
	private String nombre_contacto;
	private String apellido_contacto;
	private String celular_c;
	private String convencional_c;
	private String parentesco;
	private String id_parroquia;
	private String email;
	private String redsocial;
	private Integer id_carrera;
	private Integer nivel_cursa;
	private Integer nivel_aprobar;
	private String nombre_tutor;
	private String apellido_tutor;
	private String telefono_tutor;
	private String celular_tutor;
	private String email_tutor;
	private Integer id_modalidad;
	private String actividad;
	private Integer nohoras;
	private String diaini;
	private String diafin;
	private Integer tipo_doc;
	private String id_distg3;
	private String user;
	private Integer id_min;
	private Integer id_serv;
	private Integer id_inst;
	private Integer id_facu;
	private String id_prov;
	private String id_cant;
	private Integer id_tutor;
	private Integer id_contacto;

	private Ficha ficha;
	private Estudiante estudiante;
	private Contacto contacto;
	private Tutor tutor;
	private Date fechains;
	private List<Distacademica> listadofacultades, listadocarreras;
	private List<Distgeografica1> listadocantones, listadoparroquias;
	private List<Ficha> listadofichas;
	private List<Distvinculacion> listadoservicios, listadomodalidades;
	
	private List<String> filteredThemesc;
	private List<String> filteredThemet;
	private List<String> filteredThemests;

	@ManagedProperty(value = "#{controllerUsuarios}")
	private ControllerUsuarios controllerUsuarios;

	@ManagedProperty(value = "#{controllerEstudiante}")
	private ControllerEstudiante controllerEstudiante;
	@EJB
	private ManagerFICHA manager;
	@EJB
	private ManagerIbenef managerib;
	@EJB
	private ManagerFacultad managerf;
	@EJB
	private ManagerCarrera managerc;
	@EJB
	private ManagerServicio managers;
	@EJB
	private ManagerModalidad managerm;
	@EJB
	private ManagerParroquia managero2;
	@EJB
	private ManagerCanton managerf2;

	@EJB
	private ManagerEstudiantes managerest;
	@EJB
	private ManagerContacto managercont;
	@EJB
	private ManagerTutor managertut;

	@PostConstruct
	public void iniciar() {

		listadofichas = manager.findallFichas();
		ficha = new Ficha();
		estudiante = new Estudiante();
		contacto=new Contacto();
		tutor=new Tutor();

	}

	public void findCantonbyProv() {

		listadocantones = managerf2.findCantonbyProv(id_prov);
		id_cant = "0";
		id_parroquia = "0";

	}

	public void findParrbyCanton() {

		listadoparroquias = managero2.findParrbyCanton(id_cant);
		id_parroquia = "0";

	}

	public void findServbyMin() {
		listadoservicios = managers.findServbyMin(id_min);
		id_serv = 0;
		id_modalidad = 0;

	}

	public void findModbyServ() {
		listadomodalidades = managerm.findModalidadbyServ(id_serv);
		id_modalidad = 0;

	}

	public void findFacubyInst() {
		listadofacultades = managerf.findFacubyInst(id_inst);
		id_facu = 0;
		id_carrera = 0;

	}

	public void findCarrerabyFacu() {
		listadocarreras = managerc.findCarrerabyFacu(id_facu);
		id_carrera = 0;

	}

	public void actionListenerAgregar() {

		String user = controllerUsuarios.getCedulaUser();
		// Integer id_dist = controllerUsuarios.getId_dist();

		try {
			
			manager.crearFicha(id_ibenef, nombre_estudiante.toUpperCase(), apellido_estudiante.toUpperCase(), cedula_e, celular_e, convencional_e,
					autoidentificacion, genero, discapacidad, porcentaje, fechanac, nombre_contacto.toUpperCase(), apellido_contacto.toUpperCase(),
					celular_c, convencional_c, parentesco, id_parroquia, email, redsocial, id_carrera, nivel_cursa,
					nivel_aprobar, nombre_tutor.toUpperCase(), apellido_tutor.toUpperCase(), telefono_tutor, celular_tutor, email_tutor,
					id_modalidad, actividad, nohoras, diaini, diafin, 0, id_distg3, user, fechains, tipo_doc, true,
					estudiante);
			JSFUtil.crearMensajeInfo("Ficha ingresada exitosamente");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerFillEst() {
		Estudiante e = new Estudiante();
		e = managerest.findEstudiante(cedula_e);
		nombre_estudiante = e.getNombre();
		apellido_estudiante = e.getApellido();
		fechanac = e.getFechanac();
		genero = e.getGenero();
		autoidentificacion = e.getIdentificacion();
		discapacidad = e.getDiscapacidad();
		porcentaje = e.getPorcentajeDisc();
		id_prov = e.getDistgeografica1().getDistgeografica1().getDistgeografica1().getIdDistg1();
		findCantonbyProv();
		id_cant = e.getDistgeografica1().getDistgeografica1().getIdDistg1();
		findParrbyCanton();
		id_parroquia = e.getDistgeografica1().getIdDistg1();
		email = e.getEmail();
		redsocial = e.getRedsocial();
		tipo_doc = e.getTipo_doc();
		celular_e = e.getCelular();
		convencional_e = e.getConvencional();
		estudiante = e;

	}
	
	public void actionListenerFillCont() {
		Contacto c=new Contacto();
		System.out.println(contacto);
		c=managercont.findContacto(contacto.getIdPersona());
		nombre_contacto=c.getNombre();
		apellido_contacto=c.getApellido();
		parentesco=c.getParentesco();
		celular_c=c.getCelular();
		convencional_c=c.getConvencional();
		
	}

	public void actionListenerCargar(Ficha f) {
		tipo_doc = f.getEstudiante().getTipo_doc();
		id_ficha = f.getIdFicha();
		id_ibenef = f.getIbenef().getIdBenef();
		nombre_estudiante = f.getEstudiante().getNombre();
		apellido_estudiante = f.getEstudiante().getApellido();
		cedula_e = f.getEstudiante().getIdPersona();
		celular_e = f.getEstudiante().getCelular();
		convencional_e = f.getEstudiante().getConvencional();
		autoidentificacion = f.getEstudiante().getIdentificacion();
		genero = f.getEstudiante().getGenero();
		discapacidad = f.getEstudiante().getDiscapacidad();
		porcentaje = f.getEstudiante().getPorcentajeDisc();
		fechanac = f.getEstudiante().getFechanac();
		nombre_contacto = f.getContacto().getNombre();
		apellido_contacto = f.getContacto().getApellido();
		celular_c = f.getContacto().getCelular();
		convencional_c = f.getContacto().getConvencional();
		parentesco = f.getContacto().getParentesco();
		email = f.getEstudiante().getEmail();
		redsocial = f.getEstudiante().getRedsocial();
		nivel_cursa = f.getNivelCursa();
		nivel_aprobar = f.getNivelApro();
		nombre_tutor = f.getTutor().getNombre();
		apellido_tutor = f.getTutor().getApellido();
		telefono_tutor = f.getTutor().getConvencional();
		celular_tutor = f.getTutor().getCelular();
		email_tutor = f.getTutor().getEmail();
		actividad = f.getActividad();
		nohoras = f.getNohoras();
		diaini = f.getDiaini();
		diafin = f.getDiafin();

		id_prov = f.getEstudiante().getDistgeografica1().getDistgeografica1().getDistgeografica1().getIdDistg1();
		findCantonbyProv();
		id_cant = f.getEstudiante().getDistgeografica1().getDistgeografica1().getIdDistg1();
		findParrbyCanton();
		id_parroquia = f.getEstudiante().getDistgeografica1().getIdDistg1();

		user = f.getUsuario().getIdUsuario();
		id_min = f.getDistvinculacion().getDistvinculacion().getDistvinculacion().getIdDistvinculacion();
		findServbyMin();
		id_serv = f.getDistvinculacion().getDistvinculacion().getIdDistvinculacion();
		findModbyServ();
		id_modalidad = f.getDistvinculacion().getIdDistvinculacion();

		id_inst = f.getDistacademica().getDistacademica().getDistacademica().getIdDistacademica();
		findFacubyInst();
		id_facu = f.getDistacademica().getDistacademica().getIdDistacademica();
		findCarrerabyFacu();
		id_carrera = f.getDistacademica().getIdDistacademica();

		fechains = f.getFecha();
		id_tutor = f.getTutor().getIdPersona();
		id_contacto = f.getContacto().getIdPersona();

	}

	public void actionListenerActualizar() {

		try {
			manager.actualizarFicha(id_ficha, id_ibenef, nombre_estudiante.toUpperCase(), apellido_estudiante.toUpperCase(), cedula_e, celular_e,
					convencional_e, autoidentificacion, genero, discapacidad, porcentaje, fechanac, nombre_contacto.toUpperCase(),
					apellido_contacto.toUpperCase(), celular_c, convencional_c, parentesco, id_parroquia, email, redsocial,
					id_carrera, nivel_cursa, nivel_aprobar, nombre_tutor.toUpperCase(), apellido_tutor.toUpperCase(), telefono_tutor, celular_tutor,
					email_tutor, id_modalidad, actividad, nohoras, diaini, diafin, fechains, id_contacto, id_tutor,
					tipo_doc);
			listadofichas = manager.findallFichas();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		id_ibenef = 0;
		nombre_estudiante = "";
		apellido_estudiante = "";
		cedula_e = "";
		celular_e = "";
		convencional_e = "";
		autoidentificacion = "0";
		genero = "0";
		discapacidad = "";
		porcentaje = 999;
		nombre_contacto = "";
		apellido_contacto = "";
		celular_c = "";
		convencional_c = "";
		parentesco = "";
		id_parroquia = "";
		email = "";
		redsocial = "";
		id_carrera = 0;
		nivel_cursa = 0;
		nivel_aprobar = 0;
		fechanac = null;
		fechains = null;
		nombre_tutor = "";
		apellido_tutor = "";
		telefono_tutor = "";
		celular_tutor = "";
		email_tutor = "";
		id_modalidad = 0;
		actividad = "";
		nohoras = 0;
		diaini = "";
		diafin = "";
		id_prov = "0";
		id_inst = 0;
		id_min = 0;
		id_cant = "";
		id_facu = 0;
		id_serv = 0;
		tipo_doc = 0;
	}

	/*
	 * public List<Estudiante> autocomplete_est(String query) { filteredThemese
	 * = manager.autocomplete_est(query); return filteredThemese;
	 * 
	 * }
	 */

	public List<String> autocomplete_est(String query) {
		filteredThemests = manager.autocomplete_est(query);
		return filteredThemests;

	}

	public List<String> autocomplete_cont(String query) {
		filteredThemesc = manager.autocomplete_cont(query);
		contacto=managercont.foundonce(nombre_contacto, apellido_contacto);
		return filteredThemesc;

	}

	public Integer getId_ibenef() {
		return id_ibenef;
	}

	public void setId_ibenef(Integer id_ibenef) {
		this.id_ibenef = id_ibenef;
	}

	public String getNombre_estudiante() {
		return nombre_estudiante;
	}

	public void setNombre_estudiante(String nombre_estudiante) {
		this.nombre_estudiante = nombre_estudiante;
	}

	public String getApellido_estudiante() {
		return apellido_estudiante;
	}

	public void setApellido_estudiante(String apellido_estudiante) {
		this.apellido_estudiante = apellido_estudiante;
	}

	public String getCedula_e() {
		return cedula_e;
	}

	public void setCedula_e(String cedula_e) {
		this.cedula_e = cedula_e;
	}

	public String getCelular_e() {
		return celular_e;
	}

	public void setCelular_e(String celular_e) {
		this.celular_e = celular_e;
	}

	public String getConvencional_e() {
		return convencional_e;
	}

	public void setConvencional_e(String convencional_e) {
		this.convencional_e = convencional_e;
	}

	public String getAutoidentificacion() {
		return autoidentificacion;
	}

	public void setAutoidentificacion(String autoidentificacion) {
		this.autoidentificacion = autoidentificacion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public Integer getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(Integer porcentaje) {
		this.porcentaje = porcentaje;
	}

	public Date getFechanac() {
		return fechanac;
	}

	public void setFechanac(Date fechanac) {
		this.fechanac = fechanac;
	}

	public String getNombre_contacto() {
		return nombre_contacto;
	}

	public void setNombre_contacto(String nombre_contacto) {
		this.nombre_contacto = nombre_contacto;
	}

	public String getApellido_contacto() {
		return apellido_contacto;
	}

	public void setApellido_contacto(String apellido_contacto) {
		this.apellido_contacto = apellido_contacto;
	}

	public String getCelular_c() {
		return celular_c;
	}

	public void setCelular_c(String celular_c) {
		this.celular_c = celular_c;
	}

	public String getConvencional_c() {
		return convencional_c;
	}

	public void setConvencional_c(String convencional_c) {
		this.convencional_c = convencional_c;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getId_parroquia() {
		return id_parroquia;
	}

	public void setId_parroquia(String id_parroquia) {
		this.id_parroquia = id_parroquia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRedsocial() {
		return redsocial;
	}

	public void setRedsocial(String redsocial) {
		this.redsocial = redsocial;
	}

	public Integer getId_carrera() {
		return id_carrera;
	}

	public void setId_carrera(Integer id_carrera) {
		this.id_carrera = id_carrera;
	}

	public Integer getNivel_cursa() {
		return nivel_cursa;
	}

	public void setNivel_cursa(Integer nivel_cursa) {
		this.nivel_cursa = nivel_cursa;
	}

	public Integer getNivel_aprobar() {
		return nivel_aprobar;
	}

	public void setNivel_aprobar(Integer nivel_aprobar) {
		this.nivel_aprobar = nivel_aprobar;
	}

	public String getNombre_tutor() {
		return nombre_tutor;
	}

	public void setNombre_tutor(String nombre_tutor) {
		this.nombre_tutor = nombre_tutor;
	}

	public String getApellido_tutor() {
		return apellido_tutor;
	}

	public void setApellido_tutor(String apellido_tutor) {
		this.apellido_tutor = apellido_tutor;
	}

	public String getTelefono_tutor() {
		return telefono_tutor;
	}

	public void setTelefono_tutor(String telefono_tutor) {
		this.telefono_tutor = telefono_tutor;
	}

	public String getCelular_tutor() {
		return celular_tutor;
	}

	public void setCelular_tutor(String celular_tutor) {
		this.celular_tutor = celular_tutor;
	}

	public String getEmail_tutor() {
		return email_tutor;
	}

	public void setEmail_tutor(String email_tutor) {
		this.email_tutor = email_tutor;
	}

	public Integer getId_modalidad() {
		return id_modalidad;
	}

	public void setId_modalidad(Integer id_modalidad) {
		this.id_modalidad = id_modalidad;
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	public Integer getNohoras() {
		return nohoras;
	}

	public void setNohoras(Integer nohoras) {
		this.nohoras = nohoras;
	}

	public String getDiaini() {
		return diaini;
	}

	public void setDiaini(String diaini) {
		this.diaini = diaini;
	}

	public String getDiafin() {
		return diafin;
	}

	public void setDiafin(String diafin) {
		this.diafin = diafin;
	}

	public String getId_distg3() {
		return id_distg3;
	}

	public void setId_distg3(String id_distg3) {
		this.id_distg3 = id_distg3;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getFechains() {
		return fechains;
	}

	public void setFechains(Date fechains) {
		this.fechains = fechains;
	}

	public ControllerUsuarios getControllerUsuarios() {
		return controllerUsuarios;
	}

	public void setControllerUsuarios(ControllerUsuarios controllerUsuarios) {
		this.controllerUsuarios = controllerUsuarios;
	}

	public Integer getId_min() {
		return id_min;
	}

	public void setId_min(Integer id_min) {
		this.id_min = id_min;
	}

	public Integer getId_serv() {
		return id_serv;
	}

	public void setId_serv(Integer id_serv) {
		this.id_serv = id_serv;
	}

	public Integer getId_inst() {
		return id_inst;
	}

	public void setId_inst(Integer id_inst) {
		this.id_inst = id_inst;
	}

	public Integer getId_facu() {
		return id_facu;
	}

	public void setId_facu(Integer id_facu) {
		this.id_facu = id_facu;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
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

	public List<Distacademica> getListadofacultades() {
		return listadofacultades;
	}

	public void setListadofacultades(List<Distacademica> listadofacultades) {
		this.listadofacultades = listadofacultades;
	}

	public List<Distacademica> getListadocarreras() {
		return listadocarreras;
	}

	public void setListadocarreras(List<Distacademica> listadocarreras) {
		this.listadocarreras = listadocarreras;
	}

	public List<Distgeografica1> getListadocantones() {
		return listadocantones;
	}

	public void setListadocantones(List<Distgeografica1> listadocantones) {
		this.listadocantones = listadocantones;
	}

	public List<Distgeografica1> getListadoparroquias() {
		return listadoparroquias;
	}

	public void setListadoparroquias(List<Distgeografica1> listadoparroquias) {
		this.listadoparroquias = listadoparroquias;
	}

	public List<Ficha> getListadofichas() {
		return listadofichas;
	}

	public void setListadofichas(List<Ficha> listadofichas) {
		this.listadofichas = listadofichas;
	}

	public List<Distvinculacion> getListadoservicios() {
		return listadoservicios;
	}

	public void setListadoservicios(List<Distvinculacion> listadoservicios) {
		this.listadoservicios = listadoservicios;
	}

	public List<Distvinculacion> getListadomodalidades() {
		return listadomodalidades;
	}

	public void setListadomodalidades(List<Distvinculacion> listadomodalidades) {
		this.listadomodalidades = listadomodalidades;
	}

	public Integer getId_ficha() {
		return id_ficha;
	}

	public void setId_ficha(Integer id_ficha) {
		this.id_ficha = id_ficha;
	}

	public Integer getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(Integer tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public ControllerEstudiante getControllerEstudiante() {
		return controllerEstudiante;
	}

	public void setControllerEstudiante(ControllerEstudiante controllerEstudiante) {
		this.controllerEstudiante = controllerEstudiante;
	}

	public Integer getId_tutor() {
		return id_tutor;
	}

	public void setId_tutor(Integer id_tutor) {
		this.id_tutor = id_tutor;
	}

	public Integer getId_contacto() {
		return id_contacto;
	}

	public void setId_contacto(Integer id_contacto) {
		this.id_contacto = id_contacto;
	}

	

	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public List<String> getFilteredThemests() {
		return filteredThemests;
	}

	public void setFilteredThemests(List<String> filteredThemests) {
		this.filteredThemests = filteredThemests;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public List<String> getFilteredThemet() {
		return filteredThemet;
	}

	public void setFilteredThemet(List<String> filteredThemet) {
		this.filteredThemet = filteredThemet;
	}

	public List<String> getFilteredThemesc() {
		return filteredThemesc;
	}

	public void setFilteredThemesc(List<String> filteredThemesc) {
		this.filteredThemesc = filteredThemesc;
	}

	
	
}// end class
