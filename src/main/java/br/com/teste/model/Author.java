package br.com.teste.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Author {
	
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private LocalDateTime modified;
	private Thumbnail thumbnail;

}
