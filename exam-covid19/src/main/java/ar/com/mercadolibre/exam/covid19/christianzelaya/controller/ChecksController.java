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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.ChecksService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/covid/checks")
public class ChecksController {
	
	@Autowired
	private ChecksService checkService;
	
	/**
	 * Create a new Check in Database
	 * @param entity Check object to persist
	 * @return saved Check
	 */
	@PostMapping
	public ResponseEntity<Checks> createCheck(@RequestBody Checks check){
		check.setResult(checkService.covidResult(check.getDna()));
		return ResponseEntity.status(HttpStatus.CREATED).body(checkService.save(check));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Checks>> findCheckById(@PathVariable int id){
		Optional<Checks> check = checkService.findById(id);
		
		return ResponseEntity.ok(check);
	}
	
	@GetMapping
	public ResponseEntity<List<Checks>> findAllChecks(){
		List<Checks> checks = (List<Checks>) checkService.findAllChecks();
		return ResponseEntity.ok(checks);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Checks>> filterChecks(
		    @RequestParam (name = "key", required = true) String key,
		    @RequestParam (name = "values", required = true) String values){
		
		List<Checks> checks = (List<Checks>) checkService.filterCheck(key, values);
		return ResponseEntity.ok(checks);
		
	}
	
}
