package br.com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.model.Series;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long>{

	@Query("select series from Series series join fetch series.characters ch where ch.id = :id")
	public List<Series> listByCharactersId(Long id);
}
