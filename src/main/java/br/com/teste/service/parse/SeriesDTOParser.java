package br.com.teste.service.parse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.teste.model.Series;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.ComicController;
import br.com.teste.resource.EventController;
import br.com.teste.resource.SeriesController;
import br.com.teste.resource.StoryController;
import br.com.teste.resource.dto.ItemDTO;
import br.com.teste.resource.dto.ListItemsDTO;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.resource.dto.ThumbnailDTO;

@Component
public class SeriesDTOParser {
	
	@Autowired
	private DTOParser dtoParser;
	
	public SeriesDTO parseSeries(Series series) {
		SeriesDTO dto = new SeriesDTO();
		Long id = series.getId();
		BeanUtils.copyProperties(series, dto, "urls", "type", "thumbnail","characters", "stories","comics","events");
		
		dto.setResourceURI(dtoParser.getResourceURI(SeriesController.class, id, "series"));
		dto.setUrls(dtoParser.getURLsDTO(series.getUrls()));
		if (series.getType()!=null) {
			dto.setType(series.getType().getDescription());
		}
		
		if (series.getThumbnail()!=null) {
			dto.setThumbnail(new ThumbnailDTO(series.getThumbnail()));
		}
		
		dto.setCharacters(dtoParser.parseItems(series.getCharacters(), id, CharacterController.class, "characters"));
		dto.setStories(dtoParser.parseItems(series.getStories(), id, StoryController.class, "stories"));
		dto.setComics(dtoParser.parseItems(series.getComics(), id, ComicController.class, "comics"));
		dto.setEvents(dtoParser.parseItems(series.getEvents(), id, EventController.class, "events"));
		
		return dto;
	}
	
	public ListItemsDTO parseListSeries(List<Series> series, Long idCharacter) {
		ListItemsDTO comicsDto = new ListItemsDTO();
		
		comicsDto.setCollectionURI(dtoParser.getCollectionURI(idCharacter, "series"));
		comicsDto.setAvaliable(series.size());
		
		List<ItemDTO> itens = series.stream()
			.map(comic -> dtoParser.parseItem(comic.getTitle(), comic.getId(), SeriesController.class))
			.collect(Collectors.toList());
		comicsDto.setItens(itens);
		return comicsDto;
	}

}
