package ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.CheckRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.CheckService;

@Service
public class CheckServiceImpl implements CheckService {
	
	private static Integer cont = 1, totalSequences = 0;

	@Autowired
	private CheckRepository checkRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Checks> findAll() {
		return checkRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Checks> findAll(Pageable pageable) {
		return checkRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Checks> findById(int id) {
		if(!checkRepository.existsById(id)) {
			throw new EntityNotFoundException("El elemento buscado no existe");
		}
		return checkRepository.findById(id);
	}

	@Override
	@Transactional
	public Checks save(Checks check) {
		return checkRepository.save(check);
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		checkRepository.deleteById(id);
	}
	
	public String covidResult(String[] dna) {
		
		totalSequences = 0;
		
		horizontal(dna);
		vertical(dna);
		
		if(totalSequences < 2) {
			return "Sano";
			
		}
		else if(totalSequences>= 2 && totalSequences< 4){
			return "Infectado";
		}
		else {
			return "Inmune";
		}
	}
	
	private void horizontal(String[] dna) {
		char aux;
		char temp;
		int maximumSize;
		
		for (int i = 0; i < dna.length; i++) { 
			
			for (int j = 1; j < dna.length; j++) { 
				
				aux = dna[i].charAt(j - 1);
				temp = dna[i].charAt(j);
				maximumSize = (dna.length - j);
				
				if (!comparLetters(maximumSize, aux, temp)) { 
					break;
				}
			}
		}

	}
	
	private void vertical(String[] dna) {
		
		char aux;
		char temp;
		int maximumSize;
		
		for (int i = 0; i < dna.length; i++) {

			for (int j = 1; j < dna.length; j++) {
				
				aux = dna[j - 1].charAt(i);
				temp = dna[j].charAt(i);
				maximumSize = (dna.length - j);
				
				if (!comparLetters(maximumSize, aux, temp)) {
					break;
				}
			}
		}

	}
	
	private boolean comparLetters(int maximumSize, char aux, char temp) {
		if (temp == aux) {
			
			cont++;

			if (cont == 4) { 
				cont = 1;
				totalSequences++;
			}
		} 
		else {
			cont = 1;
			if ((maximumSize) <= 3) { 
				return false; 
				//maximum size to compare, if the string is size 6, 
				//I am standing at location 4 and the letters I compare are not the same, 
				//I advance to the next row
			}
		}
		return true;

	}

}
