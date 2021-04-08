package ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;

@Service
public class ChecksServiceImpl implements ChecksService {
	
	private static Integer cont = 1, totalSequences = 0;

	@Autowired
	private ChecksRepository checkRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Checks> findAllChecks() {
		return checkRepository.findAll();
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
	@Transactional(readOnly = true)
	public Iterable<Checks> filterCheck(String key, String result) {
		
		String[] resultArray = result.replaceAll("\\s+","").split(",");
		List<String> myList = Arrays.asList(resultArray);

		if("RESULT".equals(key.toUpperCase())) {
			return checkRepository.filterCheckResult(myList);
		}
		
		if("COUNTRY".equals(key.toUpperCase())) {
			return checkRepository.filterCheckCountry(myList);
		}
		
		throw new EntityNotFoundException("El dato ingresado para filtrar no es valido");
		
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
