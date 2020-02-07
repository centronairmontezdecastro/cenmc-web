package br.org.cenmc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@Deprecated
public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("cenmcPU");

    public EntityManager getEntityManager() {
         return emf.createEntityManager();
    }
	
}
