package it.euris.libreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.euris.libreria.data.model.CasaEditrice;

@Repository
public interface CasaEditriceRepository extends JpaRepository<CasaEditrice, Long> {

}
