package br.com.teste.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "art_work")
@Inheritance(strategy = InheritanceType.JOINED)
public class ArtWork implements LongStringObject {
	
	@Id
	private Long id;
	private String title;
	@Column(length = 500)
	private String description;
	private LocalDateTime modified;
	
	@Override
	public String stringValue() {
		return title;
	}


	@Override
	public Long longValue() {
		return id;
	}
	
//	@EqualsAndHashCode.Exclude
//	private Thumbnail thumbnail;
	
//	@EqualsAndHashCode.Exclude
//	private List<Author> creators;
//	private List<Character> characters;
	

}
