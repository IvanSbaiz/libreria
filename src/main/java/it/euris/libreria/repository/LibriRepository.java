package it.euris.libreria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.euris.libreria.data.model.Libri;

@Repository
public interface LibriRepository extends JpaRepository<Libri, Long> {
	
	List<Libri> getByTitolo(String titolo);

}
