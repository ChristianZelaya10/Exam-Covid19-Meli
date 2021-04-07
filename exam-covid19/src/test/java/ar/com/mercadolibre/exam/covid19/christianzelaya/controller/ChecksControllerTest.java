package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;


@SpringBootTest
@AutoConfigureMockMvc
public class ChecksControllerTest {
	
	@MockBean
	private ChecksService checksService;
	
	
	@MockBean
	private ChecksRepository checksRepository;
	
	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;
	
	public static final String[] DNA = {"ATGCGA",
            "CGGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"};

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	
	@Test
	public void findAllChecksTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/covid/checks"))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void getCheckByIdTest() throws Exception 
	{
		mockMvc.perform( MockMvcRequestBuilders
	      .get("/covid/checks/{id}", 1)
	      .contentType(MediaType.APPLICATION_JSON)
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andDo(print());
		
		//System.out.println(MockMvcResultMatchers.jsonPath("$.name"));
	}
	
	@Test
	public void createCheckTest() throws Exception 
	{
		Checks check = new Checks();
        check.setName("test");
        check.setCountry("Argentina");
        check.setDna(DNA);
        
        mockMvc.perform( MockMvcRequestBuilders
	      .post("/covid/checks")
	      .contentType(MediaType.APPLICATION_JSON)
	      .content(asJsonString(check))
	      .accept(MediaType.APPLICATION_JSON))
	      .andExpect(status().isCreated());
	}
	
	@Test
	public void filterChecksTest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/covid/checks/search?key=result&values=Sano,Infectado")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk());
	}
	
	
	private static String asJsonString(final Object obj) {
		try {
			return (new ObjectMapper()).writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}	
	}
