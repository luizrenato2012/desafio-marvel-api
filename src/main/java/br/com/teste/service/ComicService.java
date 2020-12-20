package br.com.teste.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.Comic;
import br.com.teste.repository.ComicRepository;
import br.com.teste.resource.CharacterController;
import br.com.teste.resource.dto.ComicDTO;
import br.com.teste.resource.dto.ItemDTO;
import br.com.teste.resource.dto.ListItemsDTO;

@Service
public class ComicService {

	@Autowired
	private ComicRepository repository;
	
	public ComicDTO findOne(Long id) {
		Comic comic = repository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Comic id "+ id + " nao encontrado"));
		DTOParser dtoParser = new DTOParser();
		ComicDTO dto = dtoParser.parseComic(comic);
		return dto;
	}
	
	public List<ComicDTO> listByCharacter(Long idCharacter) {
		List<Comic> comics = repository.listByCharactersId(idCharacter);
		if (CollectionUtils.isEmpty(comics)) {
			new ArrayList<>();
		}
		
		DTOParser parser = new DTOParser();
		return comics.stream()
			.map(comic -> parser.parseComic(comic))
			.collect(Collectors.toList());
	}

	public Comic insert(Comic comic) {
		comic.setModified(LocalDateTime.now());
		return repository.save(comic);
	}
}
