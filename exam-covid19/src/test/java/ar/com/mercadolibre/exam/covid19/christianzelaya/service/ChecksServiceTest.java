package ar.com.mercadolibre.exam.covid19.christianzelaya.service;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;


public class ChecksServiceTest {
	
	
	@Mock
    ChecksRepository repository = Mockito.mock(ChecksRepository.class);
    
	@InjectMocks
    ChecksService service;
    
    
    public static final String[] DNA = {"ATGCGA","CGGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    
    @Test
    void findById() {
    	Checks check = new Checks();
        check.setId(1);
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        check.setResult("Sano");
        
        Optional<Checks> check1 = Optional.of(check);
        Mockito.when(repository.findById(1)).thenReturn(check1);
        
    }

}
