package br.com.teste.resource.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id","title","description", "resourceURI","urls", 
		"startYear","endYear","rating", "type", "modified", "thumbnail",
		"characters"})
public class SeriesDTO {

	private Long id;
	private String title;
	private String description;
	
	private String resourceURI;
	private List<URLTypedDTO> urls;
	
	private Integer startYear;
	private Integer endYear;
	private String rating;
	private String type;
	
	private ThumbnailDTO thumbnail;
	private ListItemsDTO characters;
	private ListItemsDTO comics;
	
}

