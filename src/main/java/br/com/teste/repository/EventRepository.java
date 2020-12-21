package br.com.teste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

	@Query("select event from Event event join fetch event.characters ch where ch.id = :id")
	List<Event> listByCharactersId(Long idCharacter);

}
