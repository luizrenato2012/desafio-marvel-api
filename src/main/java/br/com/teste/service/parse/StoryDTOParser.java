package br.com.teste.service.parse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.model.Story;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.ComicController;
import br.com.teste.resource.EventController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.dto.StoryDTO;
import br.com.teste.resource.dto.ThumbnailDTO;

@Component
public class StoryDTOParser {
	
	@Autowired
	private DTOParser dtoParser;

	public StoryDTO parseStory(Story story) {
		StoryDTO dto = new StoryDTO();
		Long id = story.getId();
		BeanUtils.copyProperties(story, dto, "thumbnail", "characters", "series","comics", "events");
		
		dto.setResourceURI(dtoParser.getResourceURI(SeriesController.class, id, "story"));
		
		if (story.getThumbnail()!=null) {
		dto.setThumbnail(new ThumbnailDTO(story.getThumbnail()));
		}
		
		dto.setCharacters(dtoParser.parseItems(story.getCharacters(), id, CharacterController.class, "characters"));
		dto.setSeries(dtoParser.parseItems(story.getSeries(), id, SeriesController.class, "series"));
		dto.setComics(dtoParser.parseItems(story.getComics(), id, ComicController.class,"comics"));
		dto.setEvents(dtoParser.parseItems(story.getEvents(), id, EventController.class,"events"));
		return dto;
	}
	
	

}
