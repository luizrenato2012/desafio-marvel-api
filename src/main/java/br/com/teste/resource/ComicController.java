package br.com.teste.resource;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Comic;
import br.com.teste.resource.dto.ComicDTO;
import br.com.teste.service.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {
	
	@Autowired
	private ComicService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ComicDTO> findOne(@PathVariable Long id) {
		ComicDTO dto = service.findOne(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<Comic> insert(@RequestBody  Comic comic) {
		comic.setModified(LocalDateTime.now());
		Comic saved = service.insert(comic);
		return ResponseEntity.ok(saved);
	}
	
}
