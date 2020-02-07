package br.org.cenmc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atividade implements Serializable,Comparable<Atividade>{

	/**
	 * 
	 * 
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column ( length =255 , nullable = false )
	private String tema;
	@Enumerated(EnumType.STRING)
	private Tipo_Atividade tipoAtividade;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Temporal(TemporalType.TIME)
	private Date hora;				
	@Enumerated(EnumType.STRING) 
	private StatusAtividade statusAtividades;
	@Enumerated(EnumType.STRING)
	private DiasDaSemana diasDaSemana;
	@ManyToMany
	private List<Pessoa> participantes;
	@ManyToOne(targetEntity = Pessoa.class,fetch=FetchType.EAGER)
	@JoinColumn(name="dirigente_id")
	private Pessoa dirigente;
	
	public DiasDaSemana getDiasDaSemana() {
		return diasDaSemana;
	}
	public void setDiasDaSemana(DiasDaSemana diasDaSemana) {
		this.diasDaSemana = diasDaSemana;
	}
	
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public Tipo_Atividade getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(Tipo_Atividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
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

	public StatusAtividade getStatusAtividades() {
		return statusAtividades;
	}
	public void setStatusAtividades(StatusAtividade statusAtividades) {
		this.statusAtividades = statusAtividades;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((dirigente == null) ? 0 : dirigente.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((participantes == null) ? 0 : participantes.hashCode());
		result = prime * result + ((statusAtividades == null) ? 0 : statusAtividades.hashCode());
		result = prime * result + ((tema == null) ? 0 : tema.hashCode());
		result = prime * result + ((tipoAtividade == null) ? 0 : tipoAtividade.hashCode());
				return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dirigente == null) {
			if (other.dirigente != null)
				return false;
		} else if (!dirigente.equals(other.dirigente))
			return false;
		if (hora == null) {
			if (other.hora != null)
				return false;
		} else if (!hora.equals(other.hora))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (participantes == null) {
			if (other.participantes != null)
				return false;
		} else if (!participantes.equals(other.participantes))
			return false;
		if (statusAtividades == null) {
			if (other.statusAtividades != null)
				return false;
		} else if (!statusAtividades.equals(other.statusAtividades))
			return false;
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.equals(other.tema))
			return false;
		if (tipoAtividade != other.tipoAtividade)
			return false;	
		return true;
	}
	@Override
	public int compareTo(Atividade a) {
		int valor = data.compareTo(a.data);
		return (valor != 0 ? valor : 1);
	}

	
	

}
