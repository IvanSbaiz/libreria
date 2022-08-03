package it.euris.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.repository.AutoriRepository;
import it.euris.libreria.repository.LibriRepository;

@Service
public class LibriService {

	private LibriRepository libriRepository;
	private AutoriRepository autoriRepository;
	
	public LibriService(LibriRepository libriRepository, AutoriRepository autoriRepository) {
		this.libriRepository = libriRepository;
		this.autoriRepository = autoriRepository;
	}
	
	public Libri getById(Long id) {
		Optional<Libri> libro = libriRepository.findById(id);
		if (libro.isPresent()) {
			return libro.get();
		}
		
		return null;
	}
	
	public List<Libri> getAll() {
		return libriRepository.findAll();
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
	
	public void deleteAll() {
		libriRepository.deleteAll();
	}
	
	public void deleteByIdAutore(Long idAutore) {
		Optional<Autori> autore = autoriRepository.findById(idAutore);
		if (autore.isPresent()) {
			libriRepository.deleteByAutore(autore.get());
		}
	}

}
