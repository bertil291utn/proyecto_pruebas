package mies.controllerl;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import mies.model.entitiesl.Estado;
import mies.model.entitiesl.FichasEstado;
import mies.model.managerl.ManagerEstado;
import mies.model.managerl.ManagerFichaEstado;
import mies.view.utill.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerFichaEstado {
	private Integer id_fichaest;
	private Integer id_estado;
	private String id_estadoS;
	private Integer id_ficha;
	private Date fecha;
	private List<Estado> listadodis;
	private String observacion;
	private List<FichasEstado> listado;
	private List<FichasEstado> listadoxficha;

	@EJB
	private ManagerFichaEstado manager;
	@EJB
	private ManagerEstado managerest;

	@PostConstruct
	public void iniciar() {
		listado = manager.findallFichasEstado();

	}

	public void actionListenerAgregar() {

		try {
			manager.agregarFichaEstado(id_estado, id_ficha, observacion, fecha, listadoxficha);
			listado = manager.findallFichasEstado();
			JSFUtil.crearMensajeInfo("Estado de ficha registrada.");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
		limpiar();

	}

	public void actionListenerEliminar(int id_fichaest, FichasEstado c) {
		try {
			manager.eliminarFichaEstado(id_fichaest);
			listado = manager.findallFichasEstado();
			JSFUtil.crearMensajeInfo("Ficha: " + c.getFicha().getIdFicha() + "\n Estado:  "
					+ c.getEstado().getDescripcion() + "\n eliminado satisfactoriamente ");
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public void actionListenerCargar(FichasEstado c) {
		id_fichaest = c.getIdFichaesta();
		id_estado = c.getEstado().getIdEstado();
		id_estadoS = c.getEstado().getDescripcion();
		id_ficha = c.getFicha().getIdFicha();
		fecha = c.getFecha();
		observacion = c.getObservaciones();
	}

	public void actionListenerCargarxficha(Integer id_ficha) {
		listadoxficha = manager.findallFichasEstadoxficha(id_ficha);
		this.id_ficha = id_ficha;
		listadodis = manager.findDiscrimeEstado(managerest.findallEstado(), listadoxficha);
	}

	public void actionListenerActualizar() {

		try {
			manager.editarFichaEstado(id_fichaest, id_estado, id_ficha, observacion, fecha);
			listado = manager.findallFichasEstado();
			JSFUtil.crearMensajeInfo("Actualización correcta.");
			limpiar();
		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void limpiar() {
		fecha = null;
		id_estado = 0;
		// id_ficha = 0;
		observacion = "";

	}

	public Integer getId_fichaest() {
		return id_fichaest;
	}

	public void setId_fichaest(Integer id_fichaest) {
		this.id_fichaest = id_fichaest;
	}

	public Integer getId_estado() {
		return id_estado;
	}

	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}

	public Integer getId_ficha() {
		return id_ficha;
	}

	public void setId_ficha(Integer id_ficha) {
		this.id_ficha = id_ficha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<FichasEstado> getListado() {
		return listado;
	}

	public void setListado(List<FichasEstado> listado) {
		this.listado = listado;
	}

	public String getId_estadoS() {
		return id_estadoS;
	}

	public void setId_estadoS(String id_estadoS) {
		this.id_estadoS = id_estadoS;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<FichasEstado> getListadoxficha() {
		return listadoxficha;
	}

	public void setListadoxficha(List<FichasEstado> listadoxficha) {
		this.listadoxficha = listadoxficha;
	}

	public List<Estado> getListadodis() {
		return listadodis;
	}

	public void setListadodis(List<Estado> listadodis) {
		this.listadodis = listadodis;
	}

}// end class
