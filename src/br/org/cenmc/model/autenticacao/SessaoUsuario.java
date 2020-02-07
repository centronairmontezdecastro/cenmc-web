package br.org.cenmc.model.autenticacao;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import br.org.cenmc.model.Pessoa;


public class SessaoUsuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	public void setSessao(Pessoa pessoa) {
		if(pessoa!=null) {
			getFacesContext().getExternalContext().getSessionMap().put("usuarioLogado", pessoa);
		}
	}
	
	public Pessoa getSessao() {
		return (Pessoa) getFacesContext().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	
	public void deleteSessao() {
		getFacesContext().getExternalContext().getSessionMap().remove("usuarioLogado");
	}

}
