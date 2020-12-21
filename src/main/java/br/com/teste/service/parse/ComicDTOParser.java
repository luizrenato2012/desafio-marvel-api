package br.com.teste.service.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.com.teste.model.Comic;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.ComicController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.dto.ComicDTO;
import br.com.teste.resource.dto.ItemDTO;
import br.com.teste.resource.dto.ListItemsDTO;

@Component
public class ComicDTOParser {
	
	@Autowired
	private DTOParser dtoParser;
	
	public ComicDTO parseComic(Comic comic) {
		ComicDTO dto = new ComicDTO();
		Long id = comic.getId();
		dto.setResourceURI(WebMvcLinkBuilder.linkTo(ComicController.class)
				.slash(id)
				.withSelfRel().toUri().toString());
		BeanUtils.copyProperties(comic, dto, "images", "characters", "urls", "series");
		dto.setImages(dtoParser.parseImages(comic.getImages()));
		ListItemsDTO listItems = dtoParser.parseItems(comic.getCharacters(), id, 
				CharacterController.class, "comics");
		dto.setCharacters(listItems);
		dto.setUrls(dtoParser.getURLsDTO(comic.getUrls()));
		dto.setSeries(dtoParser.parseItems(comic.getSeries(), comic.getId(), SeriesController.class, "series"));
		return dto;
	}

	public ListItemsDTO parseComics(List<Comic> comics, Long idCharacter) {
		ListItemsDTO comicsDto = new ListItemsDTO();
		
		comicsDto.setCollectionURI(dtoParser.getCollectionURI(idCharacter, "comics"));
		comicsDto.setAvaliable(comics.size());
		
		List<ItemDTO> itens = comics.stream()
			.map(comic -> dtoParser.parseItem(comic.getTitle(), comic.getId(), ComicController.class))
			.collect(Collectors.toList());
		comicsDto.setItens(itens);
		return comicsDto;
	}

}
