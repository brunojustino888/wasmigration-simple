package br.com.wipro.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Classe da camada de dados utilit?ria para fornecer uma SessionFactory do Hibernate para os servi?os da aplica??o. 
 * @author Bruno Justino
 */
public class HibernateConfigurator {

		/**
		 * SessionFactory fornecida ap?s a cria??o e valida??o da configura??o.
		 */
		private static final SessionFactory sessionFactory;

		/**
		 * Construtor privado para n?o criar uma instancia desse objeto
		 */
		private HibernateConfigurator() {}
		
		/**
		 * M?todo est?tico utilizado para carregar e validar as configura??es da camada de acesso ? dados. 
		 */
		static {
			try {  
				final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			} catch (Throwable e) {
				throw new ExceptionInInitializerError(e);
			}
		}

		/**
		 * M?todo utilizado para fornecer o EntityManager do hibernate ? um determinado servi?o.
		 * @return EntityManager - EntityManager do hibernate.
		 */
		public static EntityManager getEntityManager() {
			EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory( "persistenceUnit" );
			return entityFactory.createEntityManager();
		}
		
		/**
		 * M?todo utilizado para fornecer a SessionFactory do hibernate ? um determinado servi?o.
		 * @return sessionFactory - SessionFactory - Sess?o do hibernate.
		 */
		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
	}