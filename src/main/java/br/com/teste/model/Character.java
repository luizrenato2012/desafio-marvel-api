package br.com.teste.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="character")
public class Character implements LongStringObject{
	
	@Id
	private Long id;
	
	private String name;
	
	@Column(length = 500)
	private String description;
	
	private LocalDateTime modified;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_thumbnail")
	private Thumbnail thumbnail;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "character_comic", 
				joinColumns = @JoinColumn(name="id_character"), 
				inverseJoinColumns = @JoinColumn(name="id_comic"))
	private List<Comic> comics;

	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "character_series", 
				joinColumns = @JoinColumn(name="id_character"), 
				inverseJoinColumns = @JoinColumn(name="id_series"))
	private List<Series> series;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "character_event", 
				joinColumns = @JoinColumn(name="id_character"), 
				inverseJoinColumns = @JoinColumn(name="id_event"))
	private List<Event> events;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "character_story", 
				joinColumns = @JoinColumn(name="id_character"), 
				inverseJoinColumns = @JoinColumn(name="id_story"))
	private List<Story> stories;
	
	@Override
	public Long longValue() {
		return id;
	}

	@Override
	public String stringValue() {
		return name;
	}
}
