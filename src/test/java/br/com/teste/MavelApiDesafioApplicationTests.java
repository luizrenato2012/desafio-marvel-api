package br.com.teste;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.teste.service.CharacterService;

@SpringBootTest
class MavelApiDesafioApplicationTests {
	
	@Autowired
	private CharacterService characterService;

	@Test
	void contextLoads() {
	}
	
}
