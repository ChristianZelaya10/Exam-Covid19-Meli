package ar.com.mercadolibre.exam.covid19.christianzelaya.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

public interface ChecksService {
	
	public Iterable<Checks> findAllChecks();
	
	public Optional<Checks> findById(int id);
	
	public Checks save(Checks check);
	
	public String covidResult(String[] dna);
	
	public Iterable<Checks> filterCheck(String key, String filter);

}
