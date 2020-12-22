package br.com.teste.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "events")
@PrimaryKeyJoinColumn(name = "id")
public class Event extends ArtWork {

	@EqualsAndHashCode.Exclude
	@OneToMany(cascade = CascadeType.ALL)
	private List<URLTyped> urls;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime start;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime end;
	
	@EqualsAndHashCode.Exclude
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="id_thumbnail")
	private Thumbnail thumbnail;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
	private List<Character> characters;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "event_story", 
				joinColumns = @JoinColumn(name="id_event"), 
				inverseJoinColumns = @JoinColumn(name="id_story"))
	private List<Story> stories;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "event_comic", 
				joinColumns = @JoinColumn(name="id_event"), 
				inverseJoinColumns = @JoinColumn(name="id_comic"))
	private List<Comic> comics;
	
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "events_series", 
				joinColumns = @JoinColumn(name="id_event"), 
				inverseJoinColumns = @JoinColumn(name="id_series"))
	private List<Series> series;
	
	

}
