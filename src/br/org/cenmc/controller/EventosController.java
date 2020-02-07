package br.org.cenmc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.dao.EventoDao;
import br.org.cenmc.model.Evento;


@Named
@ConversationScoped
public class EventosController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Conversation conversation;
	
	private static final String EVENTOS = "principal.xhtml?faces-redirect=true";
	
	private List<Evento> eventos = new ArrayList<Evento>();
    
    private Evento selectedEvento;
    
    @Inject
    private EventoDao dao;
	@PostConstruct
	public void init() {
	    if (!conversation.isTransient())
            conversation.end();
        conversation.begin();
        eventos = dao.createEventos(9);
       // return EVENTOS;
	}

	public List<Evento> getEvento() {
		return eventos;
	}

	public void setEvento(List<Evento> evento) {
		this.eventos = evento;
	}

	public Evento getSelectedEvento() {
		return selectedEvento;
	}

	public void setSelectedEvento(Evento selectedEvento) {
		this.selectedEvento = selectedEvento;
	}

	

}
