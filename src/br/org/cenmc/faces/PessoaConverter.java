package br.org.cenmc.faces;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.org.cenmc.model.Pessoa;

@FacesConverter(value = "pessoaConverter")
public class PessoaConverter implements Converter{
	
	@Inject
	private Pessoa pessoa;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		 if (value != null) {  
	            return this.pessoa;  
	        }  
	        return null;  
	    
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null && value instanceof Pessoa) {
			return ((Pessoa) value).toString();
		}
		return null;
	}
	
	

}
