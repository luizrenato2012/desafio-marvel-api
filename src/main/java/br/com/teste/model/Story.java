package br.com.teste.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "story")
@PrimaryKeyJoinColumn(name = "id")
public class Story extends ArtWork{
	
	private String type;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_thumbnail")
	private Thumbnail thumbnail;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stories")
	private List<Character> characters;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "story_comic", 
				joinColumns = @JoinColumn(name="id_story"), 
				inverseJoinColumns = @JoinColumn(name="id_comic"))
	private List<Comic> comics;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stories")
	private List<Series> series;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "stories")
	private List<Event> events;
	
	
}
