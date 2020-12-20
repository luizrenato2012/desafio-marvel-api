package br.com.teste.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.teste.model.Series;
import br.com.teste.repository.SeriesRepository;
import br.com.teste.resource.dto.SeriesDTO;

@Service
public class SeriesService {

	@Autowired
	private SeriesRepository repository;
	
	public SeriesDTO findIOne(Long id) {
		Series series = repository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Series id "+ id + " nao encontrado"));
		DTOParser dtoParser = new DTOParser();
		SeriesDTO dto = dtoParser.parseSeries(series);
		return dto;
	}
	
	public List<SeriesDTO> listByCharacter(Long idCharacter) {
		List<Series> series = repository.listByCharactersId(idCharacter);
		if (CollectionUtils.isEmpty(series)) {
			new ArrayList<>();
		}
		
		DTOParser parser = new DTOParser();
		return series.stream()
			.map(comic -> parser.parseSeries(comic))
			.collect(Collectors.toList());
	}
	
	public Series insert(Series series) {
		return repository.save(series);
	}
}
