package br.com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.model.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long>{
	
	@Query("select comic from Comic comic join fetch comic.characters ch where ch.id = :id")
	public List<Comic> listByCharactersId(Long id);

}
