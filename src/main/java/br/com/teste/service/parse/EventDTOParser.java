package br.com.teste.service.parse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.model.Event;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.StoryController;
import br.com.teste.resource.dto.EventDTO;
import br.com.teste.resource.dto.ThumbnailDTO;

@Component
public class EventDTOParser {
	
	@Autowired
	private DTOParser dtoParser;
	
	@Autowired
	private ComicDTOParser comicParser;
	
	@Autowired
	private SeriesDTOParser seriesParser;

	public EventDTO parseEvent(Event event) {
		EventDTO dto = new EventDTO();
		Long id = event.getId();
		BeanUtils.copyProperties(event, dto, "urls", "type", "thumbnail","characters","comics", "series","stories");
		
		dto.setResourceURI(dtoParser.getResourceURI(SeriesController.class, id, "event"));
		dto.setUrls(dtoParser.getURLsDTO(event.getUrls()));
		
		if (event.getThumbnail()!=null) {
			dto.setThumbnail(new ThumbnailDTO(event.getThumbnail()));
		}
		
		dto.setCharacters(dtoParser.parseItems(event.getCharacters(), id, CharacterController.class, "characters"));
		dto.setComics(comicParser.parseComics(event.getComics(), id));
		dto.setSeries(seriesParser.parseListSeries(event.getSeries(), id));
		dto.setStories(dtoParser.parseItems(event.getStories(), id, StoryController.class, "stories"));
		return dto;
	}

}
