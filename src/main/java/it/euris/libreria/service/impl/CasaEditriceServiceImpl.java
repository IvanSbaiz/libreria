package it.euris.libreria.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import it.euris.libreria.data.model.CasaEditrice;
import it.euris.libreria.data.response.GenericResponse;
import it.euris.libreria.repository.CasaEditriceRepository;
import it.euris.libreria.service.CasaEditriceService;

@Service
public class CasaEditriceServiceImpl implements CasaEditriceService {
	
	private CasaEditriceRepository casaEditriceRepository;
	
	public CasaEditriceServiceImpl(CasaEditriceRepository casaEditriceRepository) {
		this.casaEditriceRepository = casaEditriceRepository;
	}

	@Override
	public List<CasaEditrice> getAll() {
		return casaEditriceRepository.findAll();
	}

	@Override
	public CasaEditrice get(Long id) {
		Optional<CasaEditrice> casaEditrice = casaEditriceRepository.findById(id);
		if (casaEditrice.isPresent()) {
			return casaEditrice.get();
		}
		return null;
	}

	@Override
	public CasaEditrice save(CasaEditrice casaEditrice) {
		return casaEditriceRepository.save(casaEditrice);
	}

	
	public GenericResponse deleteById1(Long id) {
		GenericResponse response = new GenericResponse();
		try {
			casaEditriceRepository.deleteById(id);
			response.setStatusCode(HttpStatus.OK);
			response.setMessage("Cancellazione avvenuta");
		} catch (Exception e) {
			response.setStatusCode(HttpStatus.NOT_FOUND);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	@Override
	public Boolean deleteById(Long id) {
		CasaEditrice casa = get(id);
		if (casa != null) {
			casaEditriceRepository.deleteById(id);
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}

}
