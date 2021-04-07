package ar.com.mercadolibre.exam.covid19.christianzelaya.service.Impl;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl.ChecksServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class ChecksServiceImplTests {

	@Mock
    ChecksRepository repository;
	
	@Mock
    ChecksService service;

	
	@InjectMocks
    ChecksServiceImpl serviceImpl;
	
	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;
	
	public static final String[] DNA = {"ATGCGA","CGGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
	
	public static final String[] DNA_INMUNE = {"AAAAGA", "CGGTGC", "TTATGT", "AGAAGT", "CCCCTT", "TCACTT"};
	
	public static final String[] DNA_HEALTHY = {"ATGCGA", "CGGTAC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	
	@Test
    void findByIdTest() {
    	Checks check = new Checks();
        check.setId(1);
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        check.setResult("Sano");
        
        serviceImpl.save(check);
        
        Optional<Checks> check1 = Optional.of(check);
        //Optional<?> queryResult = repository.findById(1);
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(check1);
        
        System.out.println(check1.isPresent());
        
    };
	
	@Test
	void findAllTest() {
		
	}
	
	@Test
    void saveTest() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        Mockito.when(serviceImpl.save(check)).thenReturn(check);
        
        System.out.println(check.getResult());
        
    }
	
	@Test
    void filterTest() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        Iterable<Checks> listFilter;
        
        //Mockito.when(serviceImpl.filterCheck("RESULT", "Sano")).thenReturn([check]);
        
        System.out.println(serviceImpl.filterCheck("RESULT", "Sano"));
        
    }
	
	@Test
    void covidResultTestInfected() {
		assertEquals(serviceImpl.covidResult(DNA), "Infectado");
    }
	
	@Test
    void covidResultTestInmune() {
		assertEquals(serviceImpl.covidResult(DNA_INMUNE), "Inmune"); 
    }
	
	@Test
    void covidResultTestHealthy() {
		assertEquals(serviceImpl.covidResult(DNA_HEALTHY), "Sano"); 
    }
}