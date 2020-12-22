package br.com.teste.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "series")
@PrimaryKeyJoinColumn(name = "id")
public class Series extends ArtWork{
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<URLTyped> urls;
	
	private Integer startYear;
	private Integer endYear;
	private String rating;
	@Enumerated(EnumType.STRING)
	private TypeSeries type;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_thumbnail")
	private Thumbnail thumbnail;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "series")
	private List<Character> characters;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "series_story", 
				joinColumns = @JoinColumn(name="id_series"), 
				inverseJoinColumns = @JoinColumn(name="id_story"))
	private List<Story> stories;
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "series_comic", 
				joinColumns = @JoinColumn(name="id_series"), 
				inverseJoinColumns = @JoinColumn(name="id_comic"))
	private List<Comic> comics;

	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "series")
	private List<Event> events;
	
	
	
	
}
