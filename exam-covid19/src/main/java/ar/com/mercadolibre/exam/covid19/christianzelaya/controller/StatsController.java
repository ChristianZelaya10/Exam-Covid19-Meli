package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.exam.covid19.christianzelaya.response.Stats;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.StatsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/covid/stats")
public class StatsController {
	
	@Autowired
	private StatsService statsService;
	
	@GetMapping
	public ResponseEntity<Stats> getStats(){
		Stats stats = new Stats();
		stats.setInfected(statsService.countCheckResult("Infectado"));
		stats.setInmune(statsService.countCheckResult("Inmune"));
		stats.setHealthy(statsService.countCheckResult("Sano"));
		
		return ResponseEntity.ok(stats);
		
	}

}
