package br.com.teste.resource;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.model.Series;
import br.com.teste.resource.dto.SeriesDTO;
import br.com.teste.service.BeanDTOParser;
import br.com.teste.service.SeriesService;

@RestController
@RequestMapping("/series")
public class SeriesController {
	
	@Autowired
	private SeriesService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<SeriesDTO> findOne(@PathVariable Long id) {
		SeriesDTO dto = service.findIOne(id);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<Series> insert(@RequestBody  SeriesDTO seriesDto) {
		Series series = new BeanDTOParser().parseSeriesDTO(seriesDto);
		Series saved = service.insert(series);
		return ResponseEntity.ok(saved);
	}

}
