package br.com.wipro.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe da camada de dados utilitária para fornecer uma EntityManagerFactory do Hibernate para os serviços da aplicação. 
 * @author Bruno Justino
 */
public class HibernateJpaConfigurator { 
		
		private HibernateJpaConfigurator() {}
	
		public static EntityManager getEntityManager() {
			EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory( "persistenceUnit" );
			return entityFactory.createEntityManager();
		}
		
	}