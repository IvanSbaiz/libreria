package it.euris.libreria.data.request;

import it.euris.libreria.data.model.Autori;
import it.euris.libreria.data.model.Libri;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibriInsertRequest {
	
	private String titolo;
	private String isbn;
	private Long idAutore;
	
	public Libri toLibri() {
		Autori autore = new Autori();
		autore.setId(idAutore);
		return Libri.builder()
				.titolo(titolo)
				.isbn(isbn)
				.autore(autore)
				.build();
	}

}
