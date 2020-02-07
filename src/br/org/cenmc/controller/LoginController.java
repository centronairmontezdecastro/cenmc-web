package br.org.cenmc.controller;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.dao.PessoaDao;
import br.org.cenmc.model.Pessoa;
import br.org.cenmc.model.autenticacao.Autenticavel;
import br.org.cenmc.model.autenticacao.SessaoUsuario;

@Named
@ConversationScoped
public class LoginController implements Serializable, Autenticavel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	@Inject
	private HomeController homeController;
	@Inject
	private Conversation conversation;
	@Inject
	private SessaoUsuario sessaoUsuario;
	@Inject
	private PessoaDao dao;
	private static final String TEMPLATE_SISTEMAS_CONFIGURACAO = "principal.xhtml?faces-redirect=true";
	private static final String TEMPLATE_PORTAL = "index.xhtml?faces-redirect=true";

	public String init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();


		return TEMPLATE_PORTAL;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

//	public FacesContext getFacesContext() {
//		return FacesContext.getCurrentInstance();
//	}

	public String recuperarSenha() {
		return TEMPLATE_SISTEMAS_CONFIGURACAO;
	}

	@Override
	public String efetuarLogin() {
		Pessoa pessoa = new Pessoa();
		pessoa.setEmail(this.email);
		pessoa.setSenha(this.senha);
		this.email = "";
		this.senha = "";
		pessoa = (Pessoa) dao.existe(pessoa);
		if (pessoa != null) {
			sessaoUsuario.setSessao(pessoa);
			return TEMPLATE_SISTEMAS_CONFIGURACAO;
		}
		return TEMPLATE_PORTAL;
	}

	@Override
	public String efetuarLogout() {
		sessaoUsuario.deleteSessao();
		return TEMPLATE_PORTAL;
	}

	public boolean isLogado() {
		return sessaoUsuario.getSessao() != null;
	}

	public Pessoa getUsuario() {
		return sessaoUsuario.getSessao();
	}

	public String areaRestrita() {
		return homeController.init();
	}
}
