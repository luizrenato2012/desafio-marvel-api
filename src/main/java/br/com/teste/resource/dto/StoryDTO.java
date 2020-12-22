package br.com.teste.resource.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StoryDTO {
	
	private Long id;
	private String title;
	private String description;
	
	private String resourceURI;
	private String type;
	private LocalDateTime modified;
	private ThumbnailDTO thumbnail;
	
	private ListItemsDTO characters;
	private ListItemsDTO series;
	private ListItemsDTO comics;
	private ListItemsDTO events;

}
