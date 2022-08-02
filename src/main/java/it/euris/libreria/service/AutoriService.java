package it.euris.libreria.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.repository.AutoriRepository;

@Service
public class AutoriService {
	
	private AutoriRepository autoriRepository;
	
	public AutoriService(AutoriRepository autoriRepository) {
		this.autoriRepository = autoriRepository;
	}

	public Autori getById(Long id) {
		Optional<Autori> autore = autoriRepository.findById(id);
		if (autore.isPresent()) {
			return autore.get();
		}
		
		return null;
	}
	
	public Autori save(Autori autore) {
		return autoriRepository.save(autore);
	}
	
	public void delete(Autori autore) {
		autoriRepository.delete(autore);
	}
}
