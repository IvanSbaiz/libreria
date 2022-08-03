package it.euris.libreria;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;
import it.euris.libreria.service.AutoriService;
import it.euris.libreria.service.LibriService;
import it.euris.libreria.service.custom.AutoriServiceEM;
import it.euris.libreria.service.custom.AutoriServiceH;
import it.euris.libreria.service.custom.AutoriServiceManual;
import it.euris.libreria.service.custom.LibriServiceEM;
import it.euris.libreria.service.custom.LibriServiceH;
import it.euris.libreria.service.custom.LibriServiceManual;

@SpringBootApplication
public class LibreriaApplication {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, SQLException, IOException, JDOMException {

		ConfigurableApplicationContext context = SpringApplication.run(LibreriaApplication.class, args);
		
		AutoriService autoriServiceBean = context.getBean(AutoriService.class);
		AutoriServiceManual autoriServiceManual = new AutoriServiceManual();
		AutoriServiceEM autoriServiceEm = context.getBean(AutoriServiceEM.class);
		AutoriServiceH autoriServiceH = new AutoriServiceH();
		
		LibriService libriServiceBean = context.getBean(LibriService.class);
		LibriServiceManual libriServiceManual = new LibriServiceManual();
		LibriServiceEM libriServiceEM = context.getBean(LibriServiceEM.class);
		LibriServiceH libriServiceH = new LibriServiceH();
		
		
		libriServiceBean.deleteAll();
		autoriServiceBean.deleteAll();
		
		Autori autoreDaInserire = Autori.builder().nome("Alessandro").cognome("Manzoni").libri(new ArrayList<>()) .build();
		
		Autori autoreSalvatoBean 	= autoriServiceBean.save(autoreDaInserire);
		int recordInseritiManual 	= autoriServiceManual.insert(autoreDaInserire);
									  autoriServiceEm.insert(autoreDaInserire);
		Long idAutoreSalvatoH 		= autoriServiceH.insert(autoreDaInserire);
		
		if (autoreSalvatoBean != null) {
			System.out.println("Autore salvato BEAN: " + autoreSalvatoBean.getId() + " - " + autoreSalvatoBean.getNome() + " " + autoreSalvatoBean.getCognome());
		}
		
		if (recordInseritiManual > 0) {
			System.out.println("Record inseriti in funzione manuale: " + recordInseritiManual);
		}
		
		if (idAutoreSalvatoH != null) {
			System.out.println("ID autore salvato Hibernate: " + idAutoreSalvatoH);
		}
		
		
		Autori autore = autoriServiceBean.getById(1L);
		if (autore != null) {
			System.out.println("Autore trovato: " + autore.getId() + " - " + autore.getNome() + " " + autore.getCognome());
		}
		
		List<Libri> libriList = libriServiceBean.getByTitolo("I promessi sposi");
		for(Libri libro : libriList) {
			System.out.println("Libro trovato: " + libro.getId() + " - " + libro.getTitolo());
		}
		
		
	}

}
