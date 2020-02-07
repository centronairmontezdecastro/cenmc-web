package br.org.cenmc.faces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.org.cenmc.model.autenticacao.Papel;

@FacesConverter(value = "enumPapelConverter")
public class EnumPapelConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return Papel.valueOf(value);
		}
		return null;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && value instanceof Papel) {
			return ((Papel) value).name();
		}
		return null;
	}
}


