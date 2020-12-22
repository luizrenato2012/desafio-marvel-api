package br.com.teste.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comic")
@PrimaryKeyJoinColumn(name = "id")
public class Comic extends ArtWork{
	
	private Long digitalId;
	
	private Integer issueNumber;
	private String isbn;
	
	private String diamondCode;
	
	private String ean;
	private String issn;
	private String format;
	
	private Integer pageCount;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<URLTyped> urls;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy = "comics", fetch = FetchType.LAZY)
	private List<Character> characters;
	
	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Thumbnail>images;
	
	@ManyToMany(mappedBy = "comics", fetch = FetchType.LAZY)
	private List<Series> series;
	
	@ManyToMany(mappedBy = "comics", fetch = FetchType.LAZY)
	private List<Event> events;
	
	@ManyToMany(mappedBy = "comics", fetch = FetchType.LAZY)
	private List<Story> stories;
	
//	TODO STORIES private List<Story> stories;
//	TODO EVENTS private List<Event> events;
	

}
