package br.com.teste.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Character;
import br.com.teste.resource.dto.CharacterDTO;
import br.com.teste.resource.dto.ComicDTO;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.service.CharacterService;
import br.com.teste.service.ComicService;
import br.com.teste.service.SeriesService;

@RestController
@RequestMapping("/characters")
public class CharacterController {

	@Autowired
	private CharacterService characterService;
	
	@Autowired
	private ComicService comicService;
	
	@Autowired
	private SeriesService seriesService;
	
	@GetMapping
	public ResponseEntity<List<CharacterDTO>> listAllCharacters() {
		List<CharacterDTO> characters =  characterService.listAll();
		return ResponseEntity.ok(characters);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<CharacterDTO> findOne(@PathVariable Long id) {
		CharacterDTO character =  characterService.findOne(id);
		return ResponseEntity.ok(character);
	}
	
	@GetMapping("/{id}/comics")
	public ResponseEntity<List<ComicDTO>> findComicsByCharacter(@PathVariable Long id) {
		List<ComicDTO> comics = comicService.listByCharacter(id);
		return ResponseEntity.ok(comics);
	}
	
	@GetMapping("/{id}/series")
	public ResponseEntity<List<SeriesDTO>> findSeriesByCharacter(@PathVariable Long id) {
		List<SeriesDTO> series = seriesService.listByCharacter(id);
		return ResponseEntity.ok(series);
	}
	
	@PostMapping
	public ResponseEntity<Character> insertCharacter(@RequestBody Character character ) {
		Character salvo = characterService.insert(character);
		return ResponseEntity.ok(salvo);
	}
	
}
