package br.org.cenmc.model.autenticacao;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.org.cenmc.model.Pessoa;

public class Autorizador implements PhaseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		 	FacesContext context = event.getFacesContext();
		    String nomePagina = context.getViewRoot().getViewId();

		    //System.out.println(nomePagina);

		    if ("/index.xhtml".equals(nomePagina)) { 
		        return;
		    }
		    
		    Pessoa usuarioLogado = (Pessoa) context.getExternalContext()
		            .getSessionMap().get("usuarioLogado");

		    if(usuarioLogado != null) {
		        return;
		    }
		    
		    NavigationHandler handler = context.getApplication().getNavigationHandler();
		    handler.handleNavigation(context, null, "/index?faces-redirect=true");
		    context.renderResponse();   
		
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
