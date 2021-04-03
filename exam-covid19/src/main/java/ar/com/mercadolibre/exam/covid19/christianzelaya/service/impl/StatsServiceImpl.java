package ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.CheckRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private CheckRepository checkRepository;
	
	@Override
	@Transactional(readOnly = true)
	public int countCheckResult(String result) {
		int prueba = checkRepository.countCheckResult(result);
		return prueba;
	}
}
