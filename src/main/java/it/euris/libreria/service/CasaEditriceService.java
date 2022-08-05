package it.euris.libreria.service;

import java.util.List;

import it.euris.libreria.data.model.CasaEditrice;
import it.euris.libreria.data.response.GenericResponse;

public interface CasaEditriceService {
	
	public List<CasaEditrice> getAll();
	
	public CasaEditrice get(Long id);
	
	public CasaEditrice save(CasaEditrice casaEditrice);
	
	public Boolean deleteById(Long id);

}
