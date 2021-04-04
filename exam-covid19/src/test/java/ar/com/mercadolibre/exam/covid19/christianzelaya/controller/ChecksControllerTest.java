package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

@WebMvcTest
public class ChecksControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@SuppressWarnings("null")
	@Test
	public void checksResponseOK() throws Exception {
		Checks checks = null;
		checks.setName("Christian");
		checks.setCountry("Paraguay");
		String[] MUTANT_DNA = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		checks.setDna(MUTANT_DNA);

		mockMvc.perform(
				post("/covid/checks")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(checks)))
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
