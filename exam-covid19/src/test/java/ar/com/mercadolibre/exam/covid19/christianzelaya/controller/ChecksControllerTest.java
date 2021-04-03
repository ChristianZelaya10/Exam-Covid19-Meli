package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

@WebMvcTest
public class ChecksControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	//@Test
	//public void mutantResponseOK() throws Exception {
		//Checks checks = null;
		//checks.setName("Christian");
		//checks.setCountry("Paraguay");
		/*String[] MUTANT_DNA = {"ATGCGA", "CGGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		checks.setDna(MUTANT_DNA);
		when(dnaAnalyzer.isMutant(any(String[].class))).thenReturn(true);
		when(dataService.saveCarbonUnit(any(CarbonUnit.class))).thenReturn(mutant);

		mockMvc.perform(
				post("/mutant/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(mutant)))
				.andExpect(status().isOk());

		verify(dnaAnalyzer, times(1)).isMutant(any(String[].class));
		verify(dataService, times(1)).saveCarbonUnit(any(CarbonUnit.class));
		verifyNoMoreInteractions(dnaAnalyzer, dataService);
	}*/
	
}
