package mies.controllerl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import mies.model.entitiesl.Estudiante;
import mies.model.managerl.ManagerEstudiantes;

@ManagedBean
@RequestScoped

public class ThemeConverter implements Converter {

	@EJB
	private ManagerEstudiantes manager;

	/*
	 * @Override public Object getAsObject(FacesContext context, UIComponent
	 * component, String value) { System.out.println("estoy antes de; list");
	 * List<Estudiante> listado = new ArrayList<Estudiante>();
	 * System.out.println("ya cree un nuevo obejto de tipo List"); listado =
	 * manager.findallEstudiantes(); System.out.println("pase despues de list");
	 * for (Estudiante p : listado) { if (p.getIdPersona().equals(value)) {
	 * return p; } } return null; }
	 */

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		return manager.findEstudiante_getasobject(value);

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Estudiante) {
			Estudiante player = (Estudiante) value;
			return player.getIdPersona();
		}
		return "";
	}

}
