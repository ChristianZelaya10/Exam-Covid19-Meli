package ar.com.mercadolibre.exam.covid19.christianzelaya.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl.ChecksServiceImpl;
import ar.com.mercadolibre.exam.covid19.christianzelaya.util.ApiErrorResponse;

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
		mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	
	@Test
    void findByIdTest() {
    	Checks check = new Checks();
        check.setId(1);
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        check.setResult("Sano");
        
        repository.save(check);
        
        Optional<Checks> check1 = Optional.of(check);
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(check1);
        
        try {
            serviceImpl.findById(2);
        } catch (Exception e) {
        	assertEquals("El elemento buscado no existe", e.getMessage());
        }
        
        System.out.println(repository.findById(Mockito.anyInt()));
        
    };
	
    @Test
	void findAllTest() {
		System.out.println(serviceImpl.findAllChecks());
	}
	
	@Test
    void saveTest() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        Mockito.when(serviceImpl.save(check)).thenReturn(check);
        
    }
	
	@Test
    void filterTestResult() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        System.out.println(serviceImpl.filterCheck("RESULT", "Sano"));
        
    }
	
	@Test
    void filterTestCountry() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        System.out.println(serviceImpl.filterCheck("COUNTRY", "Argentina"));
        
    }
	
	@Test
    void filterTestError() {
    	Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        try {
        	serviceImpl.filterCheck("Error", "Argentina");
        } catch (Exception e) {
        	ApiErrorResponse error = new ApiErrorResponse();
        	error.setMessage(e.getMessage());
        	error.setMethod("GET");
        	error.setStatus(HttpStatus.BAD_REQUEST.value());
        	error.setSubErrors(null);
        	
        	assertEquals("El dato ingresado para filtrar no es valido", error.getMessage());
        	assertEquals(error.getMethod(), "GET");
        	assertEquals(error.getStatus(), 400);
        	assertEquals(error.getSubErrors(), null);
        	
        }
        
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
