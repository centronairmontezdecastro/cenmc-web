package br.org.cenmc.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import br.org.cenmc.model.Pessoa;

public class PessoaDao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PersistenceContext(name="cenmcPU")
	private EntityManager em;


	//private EntityManager em = new JPAUtil().getEntityManager();
	@Transactional
	public Pessoa existe(Pessoa pessoa) {
		if(pessoa.getEmail()!=null && pessoa.getSenha()!=null) {
			Pessoa resultado;			
			TypedQuery<Pessoa> query = em
                    .createQuery("select p from Pessoa p  where p.email = :pEmail and p.senha = :pSenha",Pessoa.class);
			//join fetch p.papeis
            query.setParameter("pEmail", pessoa.getEmail());
            query.setParameter("pSenha", pessoa.getSenha());
            try { 
            	resultado = (Pessoa) query.getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
        	return resultado;
		}
		return null;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Pessoa getPessoaPorId(Long id) {
		
			Pessoa resultado;			
			TypedQuery<Pessoa> query = em
                    .createQuery("select p from Pessoa p where p.id = :id",Pessoa.class);
			  query.setParameter("id", id);
            try { 
            	resultado =   query.getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
        	return  resultado;
    }
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Pessoa> getTodasPessoas() {
		
			List<Pessoa> resultado;			
			TypedQuery<Pessoa> query = em
                    .createQuery("select p from Pessoa p",Pessoa.class);
            try { 
            	resultado =  query.getResultList();
            } catch (NoResultException ex) {
                return null;
            }
        	return  resultado;
    }
	
	@Transactional
	public String incluir(Pessoa pessoa) {
		try {
			em.persist(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "Pessoa cadastrada com sucesso!";
	}
}
