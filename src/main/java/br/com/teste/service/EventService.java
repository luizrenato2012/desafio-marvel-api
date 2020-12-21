package br.com.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.Event;
import br.com.teste.repository.EventRepository;
import br.com.teste.resource.dto.EventDTO;
import br.com.teste.service.parse.DTOParser;
import br.com.teste.service.parse.EventDTOParser;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private EventDTOParser eventDtoParser;
	
	public EventDTO findOne(Long id) {
		Event event = repository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Event id "+ id + " nao encontrado"));
		DTOParser dtoParser = new DTOParser();
		EventDTO dto = eventDtoParser.parseEvent(event);
		return dto;
	}
	
	public List<EventDTO> listByCharacter(Long idCharacter) {
		List<Event> events = repository.listByCharactersId(idCharacter);
		if (CollectionUtils.isEmpty(events)) {
			new ArrayList<>();
		}
		
		DTOParser parser = new DTOParser();
		return events.stream()
			.map(event -> eventDtoParser.parseEvent(event))
			.collect(Collectors.toList());
	}

	public Event insert(Event event) {
		event.setModified(LocalDateTime.now());
		return repository.save(event);
	}
}
