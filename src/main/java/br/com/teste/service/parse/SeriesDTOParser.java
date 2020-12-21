package br.com.teste.service.parse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.model.Series;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.resource.dto.ThumbnailDTO;

@Component
public class SeriesDTOParser {
	
	@Autowired
	private DTOParser dtoParser;
	
	@Autowired
	private ComicDTOParser comicParser;
	
	public SeriesDTO parseSeries(Series series) {
		SeriesDTO dto = new SeriesDTO();
		Long id = series.getId();
		BeanUtils.copyProperties(series, dto, "urls", "type", "thumbnail","comics");
		
		dto.setResourceURI(dtoParser.getResourceURI(SeriesController.class, id, "series"));
		dto.setUrls(dtoParser.getURLsDTO(series.getUrls()));
		if (series.getType()!=null) {
			dto.setType(series.getType().getDescription());
		}
		if (series.getThumbnail()!=null) {
		dto.setThumbnail(new ThumbnailDTO(series.getThumbnail()));
		}
		dto.setCharacters(dtoParser.parseItems(series.getCharacters(), id, 
					CharacterController.class, "characters"));
		dto.setComics(comicParser.parseComics(series.getComics(), id));
		return dto;
	}

}
