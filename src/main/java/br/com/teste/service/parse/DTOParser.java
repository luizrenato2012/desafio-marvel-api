package br.com.teste.service.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.LongStringObject;
import br.com.teste.model.Thumbnail;
import br.com.teste.model.URLTyped;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.dto.ItemDTO;
import br.com.teste.resource.dto.ListItemsDTO;
import br.com.teste.resource.dto.ThumbnailDTO;
import br.com.teste.resource.dto.URLTypedDTO;


@Component
public class DTOParser {
	
	public List<ThumbnailDTO> parseImages(List<Thumbnail> images) {
		if (CollectionUtils.isEmpty(images)) {
			return new ArrayList<ThumbnailDTO>();
		}
		
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
			Long idCharacter, Class classController, String name) {
		if (CollectionUtils.isEmpty(longStringList)) {
			return null;
		}
		
		ListItemsDTO listItemsDto = new ListItemsDTO();
		
		listItemsDto.setCollectionURI(getCollectionURI(idCharacter, name));
		listItemsDto.setAvaliable(longStringList.size());
		
		List<ItemDTO> itens = longStringList.stream()
			.map( value -> parseItem(value.stringValue() , value.longValue(), classController))
			.collect(Collectors.toList());
		listItemsDto.setItens(itens);
		return listItemsDto;
	}
	
	public String getCollectionURI(Long idCharacter, String name) {
		Link link = WebMvcLinkBuilder.linkTo(CharacterController.class)
				.slash(idCharacter)
				.withSelfRel()
				.withName(name);
		return link.toUri().toString()+"/"+ name;
	}
	
	public String getResourceURI(Class controllerClass, Long idCharacter, String name) {
		Link link = WebMvcLinkBuilder.linkTo(controllerClass)
				.slash(idCharacter)
				.withSelfRel()
				.withName(name);
		return link.toUri().toString();
	}
	

	public List<URLTypedDTO> getURLsDTO(List<URLTyped> urls) {
		if (CollectionUtils.isEmpty(urls)) {
			return new ArrayList<>();
		}
		
		return urls.stream()
			.map(url -> new URLTypedDTO(url))
			.collect(Collectors.toList());
	}
	
}
