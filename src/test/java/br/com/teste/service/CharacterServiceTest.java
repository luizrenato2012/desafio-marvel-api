package br.com.teste.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.teste.resource.dto.CharacterDTO;

@SpringBootTest
public class CharacterServiceTest {
	
	@Autowired
	private CharacterService characterService;

	@Test
	void testCountCharacters() {
		List<CharacterDTO> dtoList = characterService.listAll();
		assertThat(dtoList).isNotNull();
		assertThat(dtoList.size()).isEqualTo(3);
	}
	
	@Test
	void testFindComics() {
		CharacterDTO dto = characterService.findOne(1009144l);
		assertThat(dto).isNotNull();
		assertThat(dto.getComics()).isNotNull();
		assertThat(dto.getComics().getItens().size()).isEqualTo(3);
	}
	
	@Test
	void testFindEvents() {
		CharacterDTO dto = characterService.findOne(1009144l);
		assertThat(dto).isNotNull();
		assertThat(dto.getEvents()).isNotNull();
		assertThat(dto.getEvents().getItens().size()).isEqualTo(5);
	}
	
	@Test
	void testFindSeries() {
		CharacterDTO dto = characterService.findOne(1009144l);
		assertThat(dto).isNotNull();
		assertThat(dto.getSeries()).isNotNull();
		assertThat(dto.getSeries().getItens().size()).isEqualTo(3);
	}
	
	@Test
	void testFindStories() {
		CharacterDTO dto = characterService.findOne(1009144l);
		assertThat(dto).isNotNull();
		assertThat(dto.getStories()).isNull();
	}
	


}
