package br.org.cenmc.controller;

import java.io.Serializable;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.model.autenticacao.Papel;
import br.org.cenmc.model.autenticacao.SessaoUsuario;

@Named
@ConversationScoped
public class MenuTopoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private SessaoUsuario sessaoUsuario;
	@Inject
	private Conversation conversation;

	@PostConstruct
	public void init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin();
	}

	public boolean verificaPermissoes(String permissoes) {
		List<Papel> papeis = sessaoUsuario.getSessao().getPapeis();

		StringTokenizer st = new StringTokenizer(permissoes, ",");
		while (st.hasMoreElements()) {
			String permissao = st.nextToken();
			if (papeis.toString().contains(permissao)) {
				return true;
			}
		}
		return false;
	}
}