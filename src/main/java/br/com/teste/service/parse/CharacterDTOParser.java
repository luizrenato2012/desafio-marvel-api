package br.com.teste.service.parse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.model.Character;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.dto.CharacterDTO;

@Component
public class CharacterDTOParser {
	
	@Autowired
	private DTOParser dtoParser;
	
	@Autowired
	private ComicDTOParser comicDtoParser;
	
	public CharacterDTO parseCharacter(Character character) {
		Long idCharacter = character.getId();
		CharacterDTO characterDTO = new CharacterDTO();
		
		characterDTO.setResourceURI(dtoParser.getResourceURI(CharacterController.class, character.getId(), "character"));
		BeanUtils.copyProperties(character, characterDTO, "comics","series");
		characterDTO.setComics(comicDtoParser.parseComics(character.getComics(), idCharacter));
		characterDTO.setSeries(dtoParser.parseItems(character.getSeries(), idCharacter, SeriesController.class, "series"));
		return characterDTO;
	}
}
