package ar.com.mercadolibre.exam.covid19.christianzelaya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

@Repository
public interface CheckRepository extends JpaRepository<Checks, Integer> {
	
	@Query(value = "select count(*) from Checks where result = (:result)")
	 int countCheckResult(@Param("result") String result) ;

}
