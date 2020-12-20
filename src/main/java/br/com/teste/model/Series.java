package br.com.teste.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

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
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@EqualsAndHashCode.Exclude
	@JoinColumn(name="id_thumbnail")
	private Thumbnail thumbnail;
	
//	creators
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.LAZY)
	
	private List<Character> characters;
//	stories
//	comics
//	events
//	next
//	previous
	
	
	
}
