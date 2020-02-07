package br.org.cenmc.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.org.cenmc.model.Atividade;
import br.org.cenmc.model.DiasDaSemana;
import br.org.cenmc.model.Pessoa;

public class AtividadeDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(name = "cenmcPU")
	private EntityManager em;
	@Inject
	private PessoaDao dao;

	@Transactional
	public String incluir(Atividade atividade) {
		try {
			em.persist(atividade);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "Atividade cadastrada com sucesso!";
	}

	@Transactional
	public List<Pessoa> buscarPessoas() {
		return dao.getTodasPessoas();
	}

	@Transactional
	public Pessoa buscarDirigentePorId(Long id) {
		return dao.getPessoaPorId(id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Atividade> buscarReunoesPublicasDoMesAtual(Date data) {

		List<Atividade> resultado;

		int diaDaSemana = 1;
		Query query = em.createNativeQuery("select * from Atividade  where MONTH(data) = :mes and YEAR(data) = :ano",
				Atividade.class);
		query.setParameter("mes", pegaMesDaData(data));
		query.setParameter("ano", pegaAnoDaData(data));
		try {

			resultado = query.getResultList();
			Collections.sort(resultado);

		} catch (NoResultException ex) {
			return null;
		}
		return resultado;
	}

	public DiasDaSemana incluirDiaDaSemana(Date data) {

		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(data);
		int diaDaSemana = dataCal.get(Calendar.DAY_OF_WEEK);
		if (diaDaSemana == 1) {
			return DiasDaSemana.DOMINGO;
		}
		if (diaDaSemana == 2) {
			return DiasDaSemana.SEGUNDA;

		}
		if (diaDaSemana == 3) {
			return DiasDaSemana.TERÇA;
		}
		if (diaDaSemana == 4) {
			return DiasDaSemana.QUARTA;
		}
		if (diaDaSemana == 5) {
			return DiasDaSemana.QUINTA;
		}
		if (diaDaSemana == 6) {
			return DiasDaSemana.SEXTA;
		}
		if (diaDaSemana == 7) {
			return DiasDaSemana.SABADO;
		}
		return DiasDaSemana.INVALIDO;

	}

	public int pegaMesDaData(Date date) {

		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(date);
		int mes = dataCal.get(Calendar.MONTH);
		if ((mes >= 0) && (mes <= 11)) {
			mes = mes + 1;
			return mes;
		}
		return 999;

	}

	public int pegaAnoDaData(Date date) {

		GregorianCalendar dataCal = new GregorianCalendar();
		dataCal.setTime(date);
		int ano = dataCal.get(Calendar.YEAR);
		return ano;

	}
	/*
	 * public void buscarDiaDaReuniaoPublica(Date data) { GregorianCalendar dataCal
	 * = new GregorianCalendar(); dataCal.setTime(data); int diasemana =
	 * dataCal.get(Calendar.DAY_OF_WEEK); switch (diasemana) { case 1:
	 * conversaoDolar(cotacao); break; case 2: conversaoDolarCanadense(); break;
	 * case 3: conversaoEuro(); break; case 0: System.out.println("-"); break; }
	 * return null; }
	 */
}
