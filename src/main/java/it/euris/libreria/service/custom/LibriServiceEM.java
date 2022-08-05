package it.euris.libreria.service.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;

@Service
public class LibriServiceEM {

	@PersistenceContext
	private EntityManager entityManager;

	public Libri getById(Long id) {
		return entityManager.getReference(Libri.class, id);
	}

	public List<Libri> getAll() {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Libri> query = cb.createQuery(Libri.class);
		Root<Libri> rootAutore = query.from(Libri.class);

		query.select(rootAutore);

		return entityManager.createQuery(query).getResultList();
	}

	public List<Libri> getByTitoloOrIsbn(String titolo, String isbn) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Libri> query = cb.createQuery(Libri.class);
		Root<Libri> rootLibri = query.from(Libri.class);

		Path<String> pathTitolo = rootLibri.get("titolo");
		Path<String> pathIsbn = rootLibri.get("isbn");

		Predicate predicateTitolo = cb.equal(pathTitolo, titolo);
		Predicate predicateIsbn = cb.equal(pathIsbn, isbn);
		Predicate predicate = cb.or(predicateTitolo, predicateIsbn);

		query.select(rootLibri).where(predicate);

		return entityManager.createQuery(query).getResultList();
	}

	public List<Libri> getByTitoloOrNomeCognomeAutore(String titolo, String nome, String cognome) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Libri> query = cb.createQuery(Libri.class);
		Root<Libri> rootLibri = query.from(Libri.class);
		Join<Libri, Autori> joinAutori = rootLibri.join(Libri.FK_COLUMN_AUTORE, JoinType.INNER);

		Path<String> pathTitolo = rootLibri.get("titolo");
		Path<String> pathName = joinAutori.get("nome");
		Path<String> pathCognome = joinAutori.get("cognome");

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.like(pathTitolo, titolo));
		predicates.add(cb.like(pathName, nome));
		predicates.add(cb.like(pathCognome, cognome));

		query.select(rootLibri).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

		return entityManager.createQuery(query).getResultList();
	}

	public void insert(Libri libro) {
		entityManager.persist(libro);
	}

	public Libri update(Libri libro) {
		return entityManager.merge(libro);
	}

	public void delete(Libri libro) {
		entityManager.remove(libro);
	}

}
