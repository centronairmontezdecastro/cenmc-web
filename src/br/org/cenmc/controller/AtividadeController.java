package br.org.cenmc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.org.cenmc.dao.AtividadeDao;
import br.org.cenmc.model.Atividade;
import br.org.cenmc.model.Pessoa;
import br.org.cenmc.model.StatusAtividade;
import br.org.cenmc.model.Tipo_Atividade;
import br.org.cenmc.model.autenticacao.SessaoUsuario;

@Named
@ConversationScoped
public class AtividadeController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Conversation conversation;
	@Inject
	private SessaoUsuario sessaoUsuario;
	@Inject
	private AtividadeDao dao;
	
	private Atividade atividade = new Atividade();
	
	private String tema;
	
	private Tipo_Atividade tipoAtividade;
	
	private Tipo_Atividade tpAtividadeEscolhida;
	
	private Date data;
	
	private Date hora;				
	
	private StatusAtividade statusAtividade;
	
	private StatusAtividade stAtividadeEscolhida;
	
	private List<Pessoa> participantes;
	
	//private Pessoa dirigenteEscolhido;
	private Long idDirigenteEscolhido;
	
	private Pessoa dirigente;
	
	private Date dataAtual = new Date();
	
	private static final String ATIVIDADES = "/view/sistema/atividade/manterAtividade.xhtml?faces-redirect=true";
	
	public String init() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		conversation.begin(); 
		return ATIVIDADES;
	}
	
//	public Pessoa getDirigenteEscolhido() {
//		return dirigenteEscolhido;
//	}
//
//	public void setDirigenteEscolhido(Pessoa dirigenteEscolhido) {
//		this.dirigenteEscolhido = dirigenteEscolhido;
//	}
	public Long getIdDirigenteEscolhido() {
		return idDirigenteEscolhido;
	}
	public void setIdDirigenteEscolhido(Long idDirigenteEscolhido) {
		this.idDirigenteEscolhido = idDirigenteEscolhido;
	}
	
	public Tipo_Atividade getTpAtividadeEscolhida() {
		return tpAtividadeEscolhida;
	}

	public void setTpAtividadeEscolhida(Tipo_Atividade tpAtividadeEscolhida) {
		this.tpAtividadeEscolhida = tpAtividadeEscolhida;
	}

	
	public StatusAtividade getStAtividadeEscolhida() {
		return stAtividadeEscolhida;
	}

	public void setStAtividadeEscolhida(StatusAtividade stAtividadeEscolhida) {
		this.stAtividadeEscolhida = stAtividadeEscolhida;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Tipo_Atividade> getTipoAtividade() {
	
		List<Tipo_Atividade> tpAtividades = new ArrayList<Tipo_Atividade>();
		
		for (Tipo_Atividade tpAtividade : Tipo_Atividade.values()) {
			tpAtividades.add(tpAtividade);
		}
		return tpAtividades;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public List<StatusAtividade> getStatusAtividade() {	
		List<StatusAtividade> atividades = new ArrayList<StatusAtividade>();
			
		for (StatusAtividade stAtividade : StatusAtividade.values()) {
			atividades.add(stAtividade);
		}
		return atividades;

		
	}

	public List<Pessoa> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Pessoa> participantes) {
		this.participantes = participantes;
	}

	public Pessoa getDirigente() {
		return dirigente;
	}

	public void setDirigente(Pessoa dirigente) {
		this.dirigente = dirigente;
	}

	public String incluir() {
		atividade.setTema(tema);
		atividade.setData(data);
		atividade.setHora(hora);
		atividade.setStatusAtividades(stAtividadeEscolhida);
		atividade.setTipoAtividade(tpAtividadeEscolhida);
		atividade.setDiasDaSemana(dao.incluirDiaDaSemana(data));
		//Pessoa dirigente = dao.buscarDirigentePorId(idDirigenteEscolhido);
		//atividade.setDirigente(dirigente);
		//atividade.setParticipantes(participantes);
		return dao.incluir(atividade);
	}
	
	public List<Pessoa> buscarParticipantes(){
		return dao.buscarPessoas();
	}
	
	public List<Pessoa> buscarDirigente(){
		return dao.buscarPessoas();
	}
	
	public List<Atividade> buscarAtividadePorMesAno(){
	//	System.out.println(dataAtual);
		return dao.buscarReunoesPublicasDoMesAtual(this.dataAtual);
	}
}
