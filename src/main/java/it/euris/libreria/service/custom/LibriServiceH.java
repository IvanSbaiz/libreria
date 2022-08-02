package it.euris.libreria.service.custom;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.euris.libreria.data.model.Libri;
import it.euris.libreria.util.HibernateUtils;

public class LibriServiceH {

	public Libri getById(Long id) {

		Session session = HibernateUtils.getSession();
		Libri libro = (Libri) session.get(Libri.class, id);
		HibernateUtils.closeSession();
		return libro;
	}

	public Long insert(Libri libro) {

		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		Long id = (Long) session.save(libro);
		t.commit();
		HibernateUtils.closeSession();
		return id;
	}

	public void update(Libri libro) {

		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.update(libro);
		t.commit();
		HibernateUtils.closeSession();
	}
	
	public void delete(Libri libro) {
		
		Session session = HibernateUtils.getSession();
		Transaction t = session.beginTransaction();
		session.remove(libro);
		t.commit();
		HibernateUtils.closeSession();
	}

}
