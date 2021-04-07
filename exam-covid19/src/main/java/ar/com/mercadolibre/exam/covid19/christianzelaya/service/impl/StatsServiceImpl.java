package ar.com.mercadolibre.exam.covid19.christianzelaya.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.mercadolibre.exam.covid19.christianzelaya.repository.ChecksRepository;
import ar.com.mercadolibre.exam.covid19.christianzelaya.service.StatsService;

@Service
public class StatsServiceImpl implements StatsService {

	@Autowired
	private ChecksRepository checkRepository;
	
	@Override
	@Transactional(readOnly = true)
	public int countCheckResult(String result) {
		return checkRepository.countCheckResult(result); 
	}
}
