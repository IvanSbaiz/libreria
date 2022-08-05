package it.euris.libreria.data.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import it.euris.libreria.data.archetype.Model;
import it.euris.libreria.data.dto.CasaEditriceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "casa_editrice")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CasaEditrice implements Model {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String nome;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "casaEditrice")
	@JsonManagedReference(value = "id_casaeditrice")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<Libri> libri;
	
	@Override
	public CasaEditriceDto toDto() {
		return CasaEditriceDto.builder().id(id).nome(nome).build();
	}

}
