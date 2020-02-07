package br.org.cenmc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.dao.PessoaDao;
import br.org.cenmc.model.Pessoa;
import br.org.cenmc.model.Sexo;
import br.org.cenmc.model.autenticacao.Papel;
import br.org.cenmc.model.autenticacao.SessaoUsuario;

@Named
@ConversationScoped
public class TrabalhadorController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;
	private String nome;
	private String email;
	private String cpf;
	private String rg;
	private Sexo sexo;
	private Date datanascimento;
	private String senha;
	@Inject
	private SessaoUsuario sessaoUsuario;
	@Inject
	private PessoaDao dao;
	private List<Papel> papelEscolhido = new ArrayList<Papel>();
	private List<Papel> permissao = new ArrayList<Papel>();
	private Pessoa pessoa = new Pessoa();
	@SuppressWarnings("unused")
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	

	private static final String TRABALHADOR = "/view/sistema/pessoa/manterTrabalhador.xhtml?faces-redirect=true";
	
	public String init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin(); 
		return TRABALHADOR;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> getPessoas() {
		return (List<Pessoa>) dao.getTodasPessoas();
	}

	public List<Papel> getPapelEscolhido() {
		return papelEscolhido;
	}

	public void setPapelEscolhido(List<Papel> papelEscolhido) {
		this.papelEscolhido = papelEscolhido;
	}
	
	public ArrayList<Papel> getPermissao() {
	       Papel[] papeis = Papel.values();
	       for (Papel papel : papeis) {
	           permissao.add(papel);
	       }
	       return (ArrayList<Papel>) permissao;
	}

	public void setPermissao(List<Papel> permissao) {
		this.permissao = permissao;
	}

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCpf() {
		return cpf;
	}



	public void setCpf(String cpf) {
		this.cpf = cpf;
	}



	public String getRg() {
		return rg;
	}



	public void setRg(String rg) {
		this.rg = rg;
	}



	public Sexo getSexo() {
		return sexo;
	}


	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}



	public Date getDatanascimento() {
		return (Date)datanascimento;
	}



	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}



	public String getSenha() {
		return senha;
	}



	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getTrabalhador() {
		return (Pessoa)sessaoUsuario.getSessao();
	}

	public String incluir() {
		this.pessoa.setPapeis(papelEscolhido);
		this.pessoa.setNome(nome);
		this.pessoa.setEmail(email);
		this.pessoa.setCpf(cpf);
		this.pessoa.setDatanascimento(datanascimento);
		this.pessoa.setSenha(senha);
		this.pessoa.setSexo(sexo);
		this.pessoa.setRg(rg);
		return dao.incluir(pessoa);
		
	}

}
