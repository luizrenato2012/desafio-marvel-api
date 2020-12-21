package br.com.teste.resource.dto;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Event;
import br.com.teste.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	private EventService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<EventDTO> findOne(@PathVariable Long id) {
		EventDTO dto = service.findOne(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<Event> insert(@RequestBody Event event) {
		event.setModified(LocalDateTime.now());
		Event saved = service.insert(event);
		return ResponseEntity.ok(saved);
	}
}
