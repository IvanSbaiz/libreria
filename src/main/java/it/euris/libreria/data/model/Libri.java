package it.euris.libreria.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import it.euris.libreria.data.archetype.Model;
import it.euris.libreria.data.dto.LibriDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "libri")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Libri implements Model {
	
	public static final String FK_COLUMN_AUTORE = "autore";
	public static final String FK_COLUMN_CASAEDITRICE = "casaeditrice";

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String titolo;

	@Column
	private String isbn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idautore")
	@JsonBackReference(value = "id_autore")
	private Autori autore;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcasaeditrice")
	@JsonBackReference(value = "id_casaeditrice")
	private CasaEditrice casaEditrice;

	@Override
	public LibriDto toDto() {
		return LibriDto.builder().id(id).titolo(titolo).isbn(isbn).autore(autore.toDto())
				.casaEditrice(casaEditrice.toDto()).build();
	}

}
