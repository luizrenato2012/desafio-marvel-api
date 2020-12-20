package br.com.teste.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Event {

	private LocalDateTime modified;
	private LocalDateTime start;
	private LocalDateTime end;
	
	private List<Story> stories;
	private List<Comic> comics;
	private List<Series> series;
	
}
