package ar.com.mercadolibre.exam.covid19.christianzelaya.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

public interface CheckService {
	
	public Iterable<Checks> findAll();
	
	public Page<Checks> findAll(Pageable pageable);
	
	public Optional<Checks> findById(int id);
	
	public Checks save(Checks check);
	
	public void deleteById(int id);
	
	public String covidResult(String[] dna);

}
