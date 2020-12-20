package br.com.teste.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import br.com.teste.model.Character;
import br.com.teste.model.Comic;
import br.com.teste.model.LongStringObject;
import br.com.teste.model.Series;
import br.com.teste.model.Thumbnail;
import br.com.teste.model.URLTyped;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.ComicController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.dto.CharacterDTO;
import br.com.teste.resource.dto.ComicDTO;
import br.com.teste.resource.dto.ItemDTO;
import br.com.teste.resource.dto.ListItemsDTO;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.resource.dto.ThumbnailDTO;
import br.com.teste.resource.dto.URLTypedDTO;

public class DTOParser {
	
	public CharacterDTO parseCharacter(Character character) {
		Long idCharacter = character.getId();
		CharacterDTO characterDTO = new CharacterDTO();
		
		characterDTO.setResourceURI(getResourceURI(CharacterController.class, character.getId(), "character"));
		BeanUtils.copyProperties(character, characterDTO, "comics","series");
		characterDTO.setComics(parseComics(character.getComics(), idCharacter));
		characterDTO.setSeries(parseItems(character.getSeries(), idCharacter, SeriesController.class));
		return characterDTO;
	}
	
	public ItemDTO parseCharacterItem(Character character) {
		ItemDTO dto = new ItemDTO();
		dto.setName(character.getName());
		dto.setResourceURI(WebMvcLinkBuilder.linkTo(CharacterController.class)
				.slash(character.getId())
				.withSelfRel().toUri().toString());
		return dto;
	}

	public ComicDTO parseComic(Comic comic) {
		ComicDTO dto = new ComicDTO();
		dto.setResourceURI(WebMvcLinkBuilder.linkTo(ComicController.class)
				.slash(comic.getId())
				.withSelfRel().toUri().toString());
		BeanUtils.copyProperties(comic, dto, "images", "characters");
		dto.setImages(parseImages(comic.getImages()));
		dto.setCharacters(parseItems(comic.getCharacters(), comic.getId(), CharacterController.class));
		return dto;
	}
	
	private List<ThumbnailDTO> parseImages(List<Thumbnail> images) {
		return images.stream()
			.map(thumbnail -> new ThumbnailDTO(thumbnail))
			.collect(Collectors.toList());
	}

	@SuppressWarnings("rawtypes")
	public ItemDTO parseItem(String name, Long id, Class controllerClass) {
		ItemDTO dto = new ItemDTO();
		dto.setName(name);
		dto.setResourceURI(WebMvcLinkBuilder.linkTo(controllerClass)
				.slash(id)
				.withSelfRel().toUri().toString());
		return dto;
	}
	
	@SuppressWarnings("rawtypes")
	public ListItemsDTO parseItems(List<? extends LongStringObject> longStringList, 
			Long idCharacter, Class classController) {
		ListItemsDTO listItemsDto = new ListItemsDTO();
		
		listItemsDto.setCollectionURI(getComicsCollectionURI(idCharacter));
		listItemsDto.setAvaliable(longStringList.size());
		
		List<ItemDTO> itens = longStringList.stream()
			.map( value -> parseItem(value.stringValue() , value.longValue(), classController))
			.collect(Collectors.toList());
		listItemsDto.setItens(itens);
		return listItemsDto;
	}
	
	public ListItemsDTO parseComics(List<Comic> comics, Long idCharacter) {
		ListItemsDTO comicsDto = new ListItemsDTO();
		
		comicsDto.setCollectionURI(getComicsCollectionURI(idCharacter));
		comicsDto.setAvaliable(comics.size());
		
		List<ItemDTO> itens = comics.stream()
			.map(comic -> parseItem(comic.getTitle(), comic.getId(), ComicController.class))
			.collect(Collectors.toList());
		comicsDto.setItens(itens);
		return comicsDto;
	}
	
	private String getComicsCollectionURI(Long idCharacter) {
		Link link = WebMvcLinkBuilder.linkTo(CharacterController.class)
				.slash(idCharacter)
				.withSelfRel()
				.withName("comics");
		return link.toUri().toString()+"/comics";
	}
	
	private String getResourceURI(Class controllerClass, Long idCharacter, String name) {
		Link link = WebMvcLinkBuilder.linkTo(controllerClass)
				.slash(idCharacter)
				.withSelfRel()
				.withName(name);
		return link.toUri().toString();
	}
	
//	private String getCharacterResourceURI(Long idCharacter) {
//		Link link = WebMvcLinkBuilder.linkTo(CharacterController.class)
//				.slash(idCharacter)
//				.withSelfRel()
//				.withName("character");
//		return link.toUri().toString();
//	}

	public SeriesDTO parseSeries(Series series) {
		SeriesDTO dto = new SeriesDTO();
		BeanUtils.copyProperties(series, dto, "urls", "type", "thumbnail");
		
		dto.setResourceURI(getResourceURI(SeriesController.class, series.getId(), "series"));
		dto.setUrls(egtURLsDTO(series.getUrls()));
		if (series.getType()!=null) {
			dto.setType(series.getType().getDescription());
		}
		if (series.getThumbnail()!=null) {
		dto.setThumbnail(new ThumbnailDTO(series.getThumbnail()));
		}
		return dto;
	}

	private List<URLTypedDTO> egtURLsDTO(List<URLTyped> urls) {
		return urls.stream()
			.map(url -> new URLTypedDTO(url))
			.collect(Collectors.toList());
	}
	
}
