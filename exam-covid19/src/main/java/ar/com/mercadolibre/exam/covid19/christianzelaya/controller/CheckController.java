package ar.com.mercadolibre.exam.covid19.christianzelaya.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.CheckService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/covid/checks")
public class CheckController {
	
	@Autowired
	private CheckService checkService;
	
	/**
	 * Create a new Check in Database
	 * @param entity Check object to persist
	 * @return saved Check
	 */
	@PostMapping
	public ResponseEntity<Checks> create(@RequestBody Checks check){
		check.setResult(checkService.covidResult(check.getDna()));
		return ResponseEntity.status(HttpStatus.CREATED).body(checkService.save(check));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Checks>> findCheckById(@PathVariable int id){
		Optional<Checks> check = checkService.findById(id);
		
		return ResponseEntity.ok(check);
	}
	
	@GetMapping
	public ResponseEntity<List<Checks>> findAll(){
		List<Checks> checks = (List<Checks>) checkService.findAll();
		return ResponseEntity.ok(checks);
		
	}
	
}
