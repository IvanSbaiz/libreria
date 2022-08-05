package it.euris.libreria.data.dto;

import it.euris.libreria.data.archetype.Dto;
import it.euris.libreria.data.model.CasaEditrice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasaEditriceDto implements Dto {
	
	private Long id;
	private String nome;
	
	@Override
	public CasaEditrice toModel() {
		return CasaEditrice.builder().id(id).nome(nome).build();
	}

}
