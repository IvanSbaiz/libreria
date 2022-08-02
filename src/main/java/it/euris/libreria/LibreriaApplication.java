package it.euris.libreria;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.service.AutoriService;
import it.euris.libreria.service.LibriService;

@SpringBootApplication
public class LibreriaApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(LibreriaApplication.class, args);
		
		AutoriService autoriService = context.getBean(AutoriService.class);
		LibriService libriService = context.getBean(LibriService.class);
		
		Autori autore = autoriService.getById(1L);
		System.out.println("Autore trovato: " + autore.getId() + " - " + autore.getNome() + " " + autore.getCognome());
		
		List<Libri> libriList = libriService.getByTitolo("I promessi sposi");
		for(Libri libro : libriList) {
			System.out.println("Libro trovato: " + libro.getId() + " - " + libro.getTitolo());
		}

	}

}
