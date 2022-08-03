package it.euris.libreria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
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
	
	public List<Autori> getAll() {
		return autoriRepository.findAll();
	}
	
	public List<Autori> getAll(String orderColumnName, Direction dir) {
		
		Sort sort = Sort.by(orderColumnName).descending();
		if (Direction.ASC.equals(dir)) {
			sort = Sort.by(orderColumnName).ascending();
		}
		
		return autoriRepository.findAll(sort);
	}
	
	public List<Autori> getAllPageable(int nrPage, int pageSize, String orderColumnName, Direction dir) {
		
		Sort sort = null;
		if (Direction.DESC.equals(dir)) {
			sort = Sort.by(orderColumnName).descending();
		} else {
			sort = Sort.by(orderColumnName).ascending();
		}
		
		Page<Autori> autoriPage = autoriRepository.findAll(PageRequest.of(nrPage, pageSize, sort));
		return autoriPage.getContent();
	}
	
	public Autori save(Autori autore) {
		Autori autoreToSave = Autori.builder().nome(autore.getNome()).cognome(autore.getCognome()).build();
		return autoriRepository.save(autoreToSave);
	}
	
	public void delete(Autori autore) {
		autoriRepository.delete(autore);
	}
	
	public void deleteById(Long idAutore) {
		autoriRepository.deleteById(idAutore);
	}
	
	public void deleteAll() {
		autoriRepository.deleteAll();
	}
}
