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
import br.com.teste.service.parse.DTOParser;
import br.com.teste.service.parse.SeriesDTOParser;

@Service
public class SeriesService {

	@Autowired
	private SeriesRepository seriesRepository;
	
	@Autowired
	private SeriesDTOParser seriesParser;
	
	public SeriesDTO findIOne(Long id) {
		Series series = seriesRepository.findById(id).orElseThrow(
				()-> new MarvelObjectNotFound("Series id ["+ id + "] nao encontrado"));
		SeriesDTO dto = seriesParser.parseSeries(series);
		return dto;
	}
	
	public List<SeriesDTO> listByCharacter(Long idCharacter) {
		List<Series> series = seriesRepository.listByCharactersId(idCharacter);
		if (CollectionUtils.isEmpty(series)) {
			new ArrayList<>();
		}
		
		DTOParser parser = new DTOParser();
		return series.stream()
			.map(comic -> seriesParser.parseSeries(comic))
			.collect(Collectors.toList());
	}
	
	public Series insert(Series series) {
		return seriesRepository.save(series);
	}
}
