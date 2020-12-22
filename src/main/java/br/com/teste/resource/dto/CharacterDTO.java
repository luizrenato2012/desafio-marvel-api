package br.com.teste.resource.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CharacterDTO {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime modified;
	private ThumbnailDTO thumbnail;
	private String resourceURI;
	private ListItemsDTO comics;
	private ListItemsDTO series;
	private ListItemsDTO events;
	private ListItemsDTO stories;
}
