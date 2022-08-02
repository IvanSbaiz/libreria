package it.euris.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Libri;
import it.euris.libreria.repository.LibriRepository;

@Service
public class LibriService {

	private LibriRepository libriRepository;
	
	public LibriService(LibriRepository libriRepository) {
		this.libriRepository = libriRepository;
	}
	
	public Libri getById(Long id) {
		Optional<Libri> libro = libriRepository.findById(id);
		if (libro.isPresent()) {
			return libro.get();
		}
		
		return null;
	}
	
	public List<Libri> getByTitolo(String titolo) {
		return libriRepository.getByTitolo(titolo);
	}
	
	public Libri save(Libri libro) {
		return libriRepository.save(libro);
	}
	
	public void delete(Libri libro) {
		libriRepository.delete(libro);
	}

}
