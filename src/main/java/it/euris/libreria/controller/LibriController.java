package it.euris.libreria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.euris.libreria.data.dto.LibriDto;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.data.request.LibriInsertRequest;
import it.euris.libreria.data.request.LibriUpdateRequest;
import it.euris.libreria.data.response.GenericResponse;
import it.euris.libreria.service.LibriService;

@RestController
@RequestMapping("/libri")
public class LibriController {
	
	private LibriService libriService;
	
	public LibriController(LibriService libriService) {
		this.libriService = libriService;
	}
	
	@GetMapping
	public List<LibriDto> getAll() {
		List<Libri> libriList = libriService.getAll();
		List<LibriDto> response = new ArrayList<>();
		libriList.forEach(libro -> response.add(libro.toDto()));
		return response;
	}
	
	@PostMapping
	public GenericResponse insert(@RequestBody LibriInsertRequest request) {
		return libriService.insert(request.toLibri());
	}
	
	@PutMapping
	public GenericResponse update(@RequestBody LibriUpdateRequest request) {
		return libriService.update(request.toLibri());
	}
	
	@DeleteMapping("/{idLibro}")
	public GenericResponse deleteById(@PathVariable Long idLibro) {
		return libriService.deleteById(idLibro);
	}

}
