package br.com.teste.resource.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDTO {
	
	private Long id;
	private String title;
	private String description;
	private String resourceURI;
	private List<URLTypedDTO> urls;
	private LocalDateTime modified;
	
	private LocalDateTime start;
	private LocalDateTime end;
	private ThumbnailDTO thumbnail;
	
	private ListItemsDTO characters;
	private ListItemsDTO comics;
	private ListItemsDTO series;

}
