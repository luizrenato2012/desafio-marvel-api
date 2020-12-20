package br.com.teste.resource.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ItemDTO {

	private String resourceURI;
	private String name;
}
