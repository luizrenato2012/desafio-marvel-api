package br.com.teste.resource.dto;

import java.time.LocalDateTime;
import java.util.List;

import br.com.teste.model.Thumbnail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComicDTO {

	private Long id;
	private Long digitalId;
	private String title;
	private String description;
	private LocalDateTime modified;
	private String isbn;
	private Integer issueNumber;
	private String diamondCode;
	private String ean;
	private String issn;
	private String format;
	private Integer pageCount;
	private String resourceURI;
	private List<ThumbnailDTO> images;
	private ListItemsDTO characters;
}
