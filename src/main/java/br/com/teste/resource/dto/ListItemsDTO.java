package br.com.teste.resource.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListItemsDTO {

	private Integer avaliable;
	private String collectionURI;
	private List<ItemDTO> itens;
	
}
