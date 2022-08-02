package it.euris.libreria.service.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;

@Service
public class AutoriServiceEM {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Autori getById(Long id) {
		return entityManager.getReference(Autori.class, id);
	}
	
	public List<Autori> getAll() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Autori> query = cb.createQuery(Autori.class);
		Root<Autori> rootAutore = query.from(Autori.class);
		
		query.select(rootAutore);
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public List<Autori> getByNomeCognome(String nome, String cognome) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Autori> query = cb.createQuery(Autori.class);
		Root<Autori> rootAutore = query.from(Autori.class);
		
		Path<String> pathNome = rootAutore.get("nome");
		Path<String> pathCognome = rootAutore.get("cognome");
		
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(pathNome, nome));
		predicates.add(cb.equal(pathCognome, cognome));
		
		query.select(rootAutore).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
		
		return entityManager.createQuery(query).getResultList();
	}
	
	public void insert(Autori autore) {
		entityManager.persist(autore);
	}
	
	public Autori update(Autori autore) {
		return entityManager.merge(autore);
	}
	
	public void delete(Autori autore) {
		entityManager.remove(autore);
	}

}
