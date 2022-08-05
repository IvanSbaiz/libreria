package it.euris.libreria.data.dto;

import it.euris.libreria.data.archetype.Dto;
import it.euris.libreria.data.model.Libri;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibriDto implements Dto {
	
	private Long id;
	private String titolo;
	private String isbn;
	private AutoriDto autore;
	private CasaEditriceDto casaEditrice;
	
	@Override
	public Libri toModel() {
		return Libri.builder().id(id).titolo(titolo).isbn(isbn).autore(autore.toModel())
				.casaEditrice(casaEditrice.toModel()).build();
	}

}
