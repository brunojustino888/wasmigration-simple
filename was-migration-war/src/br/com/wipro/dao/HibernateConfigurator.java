package br.com.wipro.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Classe da camada de dados utilitária para fornecer uma SessionFactory do Hibernate para os serviços da aplicação. 
 * @author Bruno Justino
 */
public class HibernateConfigurator {

		/**
		 * SessionFactory fornecida após a criação e validação da configuração.
		 */
		private static final SessionFactory sessionFactory;

		/**
		 * Construtor privado para não criar uma instancia desse objeto
		 */
		private HibernateConfigurator() {}
		
		/**
		 * Método estático utilizado para carregar e validar as configurações da camada de acesso à dados. 
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
		 * Método utilizado para fornecer o EntityManager do hibernate à um determinado serviço.
		 * @return EntityManager - EntityManager do hibernate.
		 */
		public static EntityManager getEntityManager() {
			EntityManagerFactory entityFactory = Persistence.createEntityManagerFactory( "persistenceUnit" );
			return entityFactory.createEntityManager();
		}
		
		/**
		 * Método utilizado para fornecer a SessionFactory do hibernate à um determinado serviço.
		 * @return sessionFactory - SessionFactory - Sessão do hibernate.
		 */
		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
	}