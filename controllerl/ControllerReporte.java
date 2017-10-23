package mies.controllerl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Distacademica;
import mies.model.entitiesl.Distvinculacion;
import mies.model.entitiesl.Ficha;
import mies.model.entitiesl.Reporte;
import mies.model.managerl.ManagerCarrera;
import mies.model.managerl.ManagerFacultad;
import mies.model.managerl.ManagerModalidad;
import mies.model.managerl.ManagerReportes;
import mies.model.managerl.ManagerServicio;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerReporte {
	private Integer mes;
	private Integer anio;
	private Integer manio;
	private Integer mdesde;
	private Integer mhasta;
	private Integer id_min;
	private Integer id_serv;
	private Integer id_mod;
	private Integer id_institucion;
	private Integer id_facu;
	private Integer id_carrera;
	private Integer total_beneficiarios;
	private Integer total_estudiante;
	private Reporte reporte;
	private Ficha ficha;
	private List<Ficha> lista;
	private List<Distvinculacion> listadoservicios, listadomodalidades;
	private List<Distacademica> listadofacultades, listadocarreras;

	@EJB
	private ManagerReportes manager;

	@EJB
	private ManagerServicio managers;
	@EJB
	private ManagerModalidad managerm;
	@EJB
	private ManagerFacultad managerf;
	@EJB
	private ManagerCarrera managerc;
	@PostConstruct
	public void iniciar() {
		reporte = new Reporte();
	}
	
	
	public void findFacubyInst() {
		setListadofacultades(managerf.findFacubyInst(id_institucion));
		id_facu = 0;
		setId_carrera(0);

	}

	public void findCarrerabyFacu() {
		setListadocarreras(managerc.findCarrerabyFacu(id_facu));
		setId_carrera(0);

	}

	public void findServbyMin() {
		setListadoservicios(managers.findServbyMin(id_min));
		id_serv = 0;
		id_mod = 0;

	}

	public void findModbyServ() {
		setListadomodalidades(managerm.findModalidadbyServ(id_serv));
		id_mod = 0;

	}

	public void FxAnio_Mes() {
		try {
			lista = manager.FxAnio_Mes(anio, mes);
			JSFUtil.crearMensajeInfo("Datos encontrados");
		} catch (Exception e) {
			if (lista != null)
				lista.clear();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		reporte = manager.total_beneficiarios(lista);
		reporte = manager.total_estudiantes(lista);
		reporte = manager.total_nhombresnmujeres(lista);

	}

	public void FxMeses() {
		try {
			lista = manager.FxMeses(mdesde, mhasta, manio);
			JSFUtil.crearMensajeInfo("Datos encontrados");
		} catch (Exception e) {
			if (lista != null)
				lista.clear();
			JSFUtil.crearMensajeError(e.getMessage());

		}
		reporte = manager.total_beneficiarios(lista);
		reporte = manager.total_estudiantes(lista);
		reporte = manager.total_nhombresnmujeres(lista);

	}

	public void FxVinculacion() {
		try {
			lista = manager.FxVinculacion(id_min, id_serv, id_mod);
			JSFUtil.crearMensajeInfo("Datos encontrados");
		} catch (Exception e) {
			if (lista != null)
				lista.clear();
			JSFUtil.crearMensajeError(e.getMessage());

		}
		reporte = manager.total_beneficiarios(lista);
		reporte = manager.total_estudiantes(lista);
		reporte = manager.total_nhombresnmujeres(lista);

	}

	
	public void FxIes() {
		try {
			lista = manager.FxIes(id_institucion,id_facu,id_carrera);
			JSFUtil.crearMensajeInfo("Datos encontrados");
		} catch (Exception e) {
			if (lista != null)
				lista.clear();
			JSFUtil.crearMensajeError(e.getMessage());

		}
		reporte = manager.total_beneficiarios(lista);
		reporte = manager.total_estudiantes(lista);
		reporte = manager.total_nhombresnmujeres(lista);

	}

	public void limpiar() {
		id_min = 0;
		id_serv = 0;
		id_mod = 0;
		id_institucion = 0;
		mes = 0;
		mdesde = 0;
		mhasta = 0;
		id_institucion=0;
		id_facu=0;
		id_carrera=0;

	}

	public void nuevo() {

		limpiar();
		if (lista != null)
			lista.clear();
		if(listadomodalidades!=null)
			listadomodalidades.clear();
		if(listadoservicios!=null)
			listadoservicios.clear();
		if(listadocarreras!=null)
			listadocarreras.clear();
		if(listadofacultades!=null)
			listadofacultades.clear();
		reporte = new Reporte();
		anio = null;
		manio = null;

	}

	// getters and setters

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMdesde() {
		return mdesde;
	}

	public void setMdesde(Integer mdesde) {
		this.mdesde = mdesde;
	}

	public Integer getMhasta() {
		return mhasta;
	}

	public void setMhasta(Integer mhasta) {
		this.mhasta = mhasta;
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

	public Integer getId_mod() {
		return id_mod;
	}

	public void setId_mod(Integer id_mod) {
		this.id_mod = id_mod;
	}

	public Integer getId_institucion() {
		return id_institucion;
	}

	public void setId_institucion(Integer id_institucion) {
		this.id_institucion = id_institucion;
	}

	public Reporte getReporte() {
		return reporte;
	}

	public void setReporte(Reporte reporte) {
		this.reporte = reporte;
	}

	public List<Ficha> getLista() {
		return lista;
	}

	public void setLista(List<Ficha> lista) {
		this.lista = lista;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Integer getManio() {
		return manio;
	}

	public void setManio(Integer manio) {
		this.manio = manio;
	}

	public Integer getTotal_beneficiarios() {
		return total_beneficiarios;
	}

	public void setTotal_beneficiarios(Integer total_beneficiarios) {
		this.total_beneficiarios = total_beneficiarios;
	}

	public Integer getTotal_estudiante() {
		return total_estudiante;
	}

	public void setTotal_estudiante(Integer total_estudiante) {
		this.total_estudiante = total_estudiante;
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


	public Integer getId_carrera() {
		return id_carrera;
	}


	public void setId_carrera(Integer id_carrera) {
		this.id_carrera = id_carrera;
	}


	public Integer getId_facu() {
		return id_facu;
	}


	public void setId_facu(Integer id_facu) {
		this.id_facu = id_facu;
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
	
	

}// end class
