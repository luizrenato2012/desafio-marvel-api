package br.com.teste.resource.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicDTO {

	private Long id;
	private Long digitalId;
	private String title;
	private Integer issueNumber;
	private String description;
	private LocalDateTime modified;
	private String isbn;
	private String diamondCode;
	private String ean;
	private String issn;
	private String format;
	private Integer pageCount;
	private String resourceURI;
	private List<URLTypedDTO> urls;
	private ListItemsDTO series;
	private List<ThumbnailDTO> images;
	private ListItemsDTO characters;
	private ListItemsDTO stories;
	private ListItemsDTO events;
}
