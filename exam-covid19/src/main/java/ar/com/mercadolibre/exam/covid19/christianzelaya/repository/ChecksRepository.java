package ar.com.mercadolibre.exam.covid19.christianzelaya.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.mercadolibre.exam.covid19.christianzelaya.entity.Checks;

@Repository
public interface ChecksRepository extends JpaRepository<Checks, Integer> {
	
	@Query(value = "select count(*) from Checks where result = (:result)")
	 int countCheckResult(@Param("result") String result);
	
	@Query(value = "SELECT * FROM checks where result IN :filter", nativeQuery = true)
	Iterable<Checks> filterCheckResult(@Param("filter") List<String> filter);
	
	@Query(value = "SELECT * FROM checks where country IN :filter", nativeQuery = true)
	Iterable<Checks> filterCheckCountry(@Param("filter") List<String> filter);


}
