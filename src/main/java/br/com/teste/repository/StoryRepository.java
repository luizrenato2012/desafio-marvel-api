package br.com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{

	@Query("select stories from Story stories join fetch stories.characters ch where ch.id = :id")
	public List<Story> listByCharactersId(Long id);
}
