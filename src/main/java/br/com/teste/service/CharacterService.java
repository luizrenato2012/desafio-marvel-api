package br.com.teste.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.model.Character;
import br.com.teste.repository.CharacterRepository;
import br.com.teste.resource.dto.CharacterDTO;
import br.com.teste.service.parse.CharacterDTOParser;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository repository;
	
	@Autowired
	private CharacterDTOParser characterDTOParser;
	
	public br.com.teste.model.Character insert(br.com.teste.model.Character character) {
		character.setModified(LocalDateTime.now());
		return repository.save(character);
	}
	
	public List<CharacterDTO> listAll() {
		List<Character> characters = repository.findAll();
		return characters.stream()
			.map(character -> characterDTOParser.parseCharacter(character))
			.collect(Collectors.toList());
	}
	
	public CharacterDTO findOne(long id) {
		Character character = repository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Character id ["+ id +" nao encontrado"));
		return characterDTOParser.parseCharacter(character);
	}
	
}
