package it.euris.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.euris.libreria.data.model.Autori;

@Repository
public interface AutoriRepository extends JpaRepository<Autori, Long> {

}
