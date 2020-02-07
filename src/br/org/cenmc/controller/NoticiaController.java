package br.org.cenmc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.dao.NoticiaDao;
import br.org.cenmc.model.Noticia;
import br.org.cenmc.model.autenticacao.SessaoUsuario;

@Named
@ConversationScoped
public class NoticiaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;
	@SuppressWarnings("unused")
	@Inject
	private SessaoUsuario sessaoUsuario;
	
	@Inject
	private NoticiaDao dao;
	private String titulo;
	private String texto;
	private Date data;
	private Noticia noticia = new Noticia();
	
private static final String NOTICIA = "/view/sistema/noticia/manterNoticia.xhtml?faces-redirect=true";
	
	public String init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin(); 
		return NOTICIA;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Noticia getNoticia() {
		return noticia;
	}
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
	public List<Noticia> todasNoticias(){
		return dao.todasNoticias();
	}

	public List<Noticia> exibeNoticia() {

		return dao.exibirNoticia();
			
	}
	
	public String incluir() {
		noticia.setTitulo(titulo);
		noticia.setData(new Date());
		noticia.setTexto(texto);
		return dao.incluir(noticia);
		
	}

	
}
