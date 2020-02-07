package br.org.cenmc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.org.cenmc.model.Noticia;
import br.org.cenmc.model.Pessoa;

public class NoticiaDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(name="cenmcPU")
	private EntityManager em;
	
	@Transactional
	public String incluir(Noticia noticia) {
		
		try {
			em.persist(noticia);
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		return "Notícia cadastrada com sucesso!";
	}
	
	
	@Transactional
	public List<Noticia> todasNoticias() {
		List<Noticia> resultado;	
		TypedQuery<Noticia> query = em
                .createQuery("select n from Noticia n ",Noticia.class);
		  try { 
          	resultado = (List<Noticia>) query.getResultList();
          } catch (NoResultException ex) {
              return null;
          }
      	return  resultado;
	}
	
	@Transactional
	public List<Noticia> exibirNoticia() {
		List<Noticia> resultado;	
		TypedQuery<Noticia> query = em
                .createQuery("select n from Noticia n ORDER BY n.data DESC",Noticia.class);
		  try { 
			query.setMaxResults(5);
          	resultado = (List<Noticia>) query.getResultList();
          } catch (NoResultException ex) {
              return null;
          }
      	return  resultado;
	}

}
