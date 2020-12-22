package br.com.teste.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Story;
import br.com.teste.resource.dto.StoryDTO;
import br.com.teste.service.StoryService;

@RestController
@RequestMapping("/stories")
public class StoryController {
	
	@Autowired
	private StoryService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<StoryDTO> findOne(@PathVariable Long id) {
		StoryDTO dto = service.findIOne(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<Story> insert(@RequestBody  Story story) {
		Story saved = service.insert(story);
		return ResponseEntity.ok(saved);
	}

}
