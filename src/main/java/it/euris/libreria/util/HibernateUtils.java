package it.euris.libreria.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
	
	private static SessionFactory sessionFactory;
	private static Session session;
	
	public static Session getSession() {
		
		if (session == null) {
			createSession();
		}
		
		return session;
	}
	
	public static void closeSession() {
		
		if (sessionFactory != null) {
			sessionFactory.close();
		}
		if (session != null) {
			session.close();
		}
	}
	
	private static void createSession() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		if (sessionFactory == null) {
			createSessionFactory(meta);
		}
		session = sessionFactory.openSession();
	}
	
	private static void createSessionFactory(Metadata meta) {
		sessionFactory = meta.getSessionFactoryBuilder().build();
	}

}
