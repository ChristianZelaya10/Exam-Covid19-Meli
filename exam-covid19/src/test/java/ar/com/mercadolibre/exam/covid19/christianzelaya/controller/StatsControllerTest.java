package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;

@SpringBootTest
@AutoConfigureMockMvc
public class StatsControllerTest {
	
	@MockBean
	private ChecksService checksService;
	
	@MockBean
	private ChecksRepository checksRepository;
	
	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	
	@Test
	public void findAllChecksTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/covid/stats"))
		.andExpect(MockMvcResultMatchers.status().is(200));
	}

}
