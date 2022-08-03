package it.euris.libreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibreriaApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibreriaApplication.class, args);
		
//		AutoriService autoriServiceBean = context.getBean(AutoriService.class);
//		AutoriServiceManual autoriServiceManual = new AutoriServiceManual();
//		AutoriServiceEM autoriServiceEm = context.getBean(AutoriServiceEM.class);
//		AutoriServiceH autoriServiceH = new AutoriServiceH();
//		
//		LibriService libriServiceBean = context.getBean(LibriService.class);
//		LibriServiceManual libriServiceManual = new LibriServiceManual();
//		LibriServiceEM libriServiceEM = context.getBean(LibriServiceEM.class);
//		LibriServiceH libriServiceH = new LibriServiceH();
//		
//		
//		libriServiceBean.deleteAll();
//		autoriServiceBean.deleteAll();
//		
//		Autori autoreDaInserire1 = Autori.builder().nome("Alessandro1").cognome("Manzoni1").build();
//		Autori autoreDaInserire2 = Autori.builder().nome("Alessandro2").cognome("Manzoni2").build();
//		Autori autoreDaInserire3 = Autori.builder().nome("Alessandro3").cognome("Manzoni3").build();
//		Autori autoreDaInserire4 = Autori.builder().nome("Alessandro4").cognome("Manzoni4").build();
//		Autori autoreDaInserire5 = Autori.builder().nome("Alessandro5").cognome("Manzoni5").build();
//		Autori autoreDaInserire6 = Autori.builder().nome("Alessandro6").cognome("Manzoni6").build();
//		
//		autoriServiceBean.save(autoreDaInserire1);
//		autoriServiceBean.save(autoreDaInserire2);
//		autoriServiceBean.save(autoreDaInserire3);
//		autoriServiceBean.save(autoreDaInserire4);
//		autoriServiceBean.save(autoreDaInserire5);
//		autoriServiceBean.save(autoreDaInserire6);
//		
//		List<Autori> autoriList = autoriServiceBean.getAllPageable(0, 10, "nome", Direction.DESC);
//		for (Autori autore : autoriList) {
//			System.out.println(autore.getId() + " - " + autore.getNome() + " " + autore.getCognome());
//		}
//		
//		AutoriDto autoriDto = new AutoriDto();
//		Autori autore = autoriDto.toModel();
//		autore.setLibri(libriServiceBean.getAll());
		
//		int recordInseritiManual 	= autoriServiceManual.insert(autoreDaInserire);
//									  autoriServiceEm.insert(autoreDaInserire);
//		Long idAutoreSalvatoH 		= autoriServiceH.insert(autoreDaInserire);
//		
//		if (autoreSalvatoBean != null) {
//			System.out.println("Autore salvato BEAN: " + autoreSalvatoBean.getId() + " - " + autoreSalvatoBean.getNome() + " " + autoreSalvatoBean.getCognome());
//		}
//		
//		if (recordInseritiManual > 0) {
//			System.out.println("Record inseriti in funzione manuale: " + recordInseritiManual);
//		}
//		
//		if (idAutoreSalvatoH != null) {
//			System.out.println("ID autore salvato Hibernate: " + idAutoreSalvatoH);
//		}
//		
//		
//		Autori autore = autoriServiceBean.getById(1L);
//		if (autore != null) {
//			System.out.println("Autore trovato: " + autore.getId() + " - " + autore.getNome() + " " + autore.getCognome());
//		}
//		
//		List<Libri> libriList = libriServiceBean.getByTitolo("I promessi sposi");
//		for(Libri libro : libriList) {
//			System.out.println("Libro trovato: " + libro.getId() + " - " + libro.getTitolo());
//		}
		
		
	}

}
