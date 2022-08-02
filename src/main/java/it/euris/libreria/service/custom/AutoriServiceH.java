package it.euris.libreria.service.custom;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.util.HibernateUtils;

public class AutoriServiceH {
	
	public Autori getById(Long id) {
		
		Session session = HibernateUtils.getSession();
		Autori autore = (Autori) session.get(Autori.class, id);
		HibernateUtils.closeSession();
		return autore;
	}
	
	public Long insert(Autori autore) {
		
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		Long id = (Long) session.save(autore);
		t.commit();
		HibernateUtils.closeSession();
		return id;
	}
	
	public void update(Autori autore) {
		
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.update(autore);
		t.commit();
		HibernateUtils.closeSession();
	}
	
	public void delete(Autori autore) {
		
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.remove(autore);
		t.commit();
		HibernateUtils.closeSession();
	}

}
