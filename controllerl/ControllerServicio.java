package mies.controllerl;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mies.model.entitiesl.Distvinculacion;
import mies.model.managerl.ManagerMinisterio;
import mies.model.managerl.ManagerServicio;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerServicio {
	private int id_serv;
	private int id_min;
	private String id_minS;
	private String nombreServ;
	private List<Distvinculacion> listado;
	private List<Distvinculacion> listadom;

	@EJB
	private ManagerServicio manager;
	@EJB
	private ManagerMinisterio managerm;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallServicios();
		listadom = managerm.findallMinisterio();
	}

	public void actionListenerAgregar() {

		try {
			manager.agregarServicio(id_min, nombreServ.toUpperCase());
			listado = manager.findallServicios();
			JSFUtil.crearMensajeInfo("Servicio registrado");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_serv, Distvinculacion s) {
		try {
			manager.eliminarServicio(id_serv);
			listado = manager.findallServicios();
			JSFUtil.crearMensajeInfo("Servicio\n " + s.getDescripcion() + " eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(Distvinculacion s) {
		id_serv = s.getIdDistvinculacion();
		id_min = s.getDistvinculacion().getIdDistvinculacion();
		id_minS = s.getDistvinculacion().getDescripcion();
		nombreServ = s.getDescripcion();
	}

	public void actionListenerActualizar() {

		try {
			manager.editarServicio(id_serv, id_min, nombreServ.toUpperCase());
			listado = manager.findallServicios();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		id_min=0;
		nombreServ = "";

	}

	public int getId_serv() {
		return id_serv;
	}

	public void setId_serv(int id_serv) {
		this.id_serv = id_serv;
	}

	public int getId_min() {
		return id_min;
	}

	public void setId_min(int id_min) {
		this.id_min = id_min;
	}

	public String getNombreServ() {
		return nombreServ;
	}

	public void setNombreServ(String nombreServ) {
		this.nombreServ = nombreServ;
	}

	public List<Distvinculacion> getListado() {
		return listado;
	}

	public void setListado(List<Distvinculacion> listado) {
		this.listado = listado;
	}

	public List<Distvinculacion> getListadom() {
		return listadom;
	}

	public void setListadom(List<Distvinculacion> listadom) {
		this.listadom = listadom;
	}

	public String getId_minS() {
		return id_minS;
	}

	public void setId_minS(String id_minS) {
		this.id_minS = id_minS;
	}

}// end class
