package br.com.teste.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Character;
import br.com.teste.model.Comic;
import br.com.teste.repository.CharacterRepository;
import br.com.teste.repository.ComicRepository;
import br.com.teste.service.CharacterService;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private ComicRepository comicRepository;
	
	@Autowired
	private CharacterRepository	characterRepository;
	
	@Autowired
	private CharacterService characterService;
	
	/** ------------------- Comics -------------------------- */
	@PostMapping("/comics")
	public ResponseEntity<Comic> insertComic(@RequestBody Comic comic) {
		Comic salvo = comicRepository.save(comic);
		return ResponseEntity.ok(salvo);
	}
	
//	@GetMapping("/comics")
//	public ResponseEntity<List<Comic>> listAllComic() {
//		List<Comic> comics =  comicRepository.findAll();
//		return ResponseEntity.ok(comics);
//	}
	
	/**  ----------------- characters ----------------------- */
	@PostMapping("/characters")
	public ResponseEntity<Character> insertCharacter(@RequestBody Character character ) {
		Character salvo = characterService.insert(character);
		return ResponseEntity.ok(salvo);
	}
	
//	@GetMapping("/characters")
//	public ResponseEntity<List<Comic>> listAllCharacters() {
//		List<Comic> comics =  comicRepository.findAll();
//		return ResponseEntity.ok(comics);
//	}
	
}
