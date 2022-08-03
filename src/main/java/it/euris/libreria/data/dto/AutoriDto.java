package it.euris.libreria.data.dto;

import it.euris.libreria.data.archetype.Dto;
import it.euris.libreria.data.model.Autori;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutoriDto implements Dto {
	
	private Long id;
	private String nome;
	private String cognome;
	
	public String getNomeCognome() {
		return nome + " " + cognome;
	}

	@Override
	public Autori toModel() {
		
		return Autori.builder().id(id).nome(nome).cognome(cognome).build();
	}

}
