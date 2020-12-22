package br.com.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.Story;
import br.com.teste.repository.StoryRepository;
import br.com.teste.resource.dto.StoryDTO;
import br.com.teste.service.parse.DTOParser;
import br.com.teste.service.parse.StoryDTOParser;

@Service
public class StoryService {
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private StoryDTOParser storyParser;
	
	public StoryDTO findIOne(Long id) {
		Story stories = storyRepository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Story id ["+ id + "] nao encontrado"));
		StoryDTO dto = storyParser.parseStory(stories);
		return dto;
	}
	
	public List<StoryDTO> listByCharacter(Long idCharacter) {
		List<Story> stories = storyRepository.listByCharactersId(idCharacter);
		if (CollectionUtils.isEmpty(stories)) {
			new ArrayList<>();
		}
		
		DTOParser parser = new DTOParser();
		return stories.stream()
			.map(comic -> storyParser.parseStory(comic))
			.collect(Collectors.toList());
	}
	
	public Story insert(Story stories) {
		return storyRepository.save(stories);
	}

}
