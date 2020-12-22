package br.com.teste.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="thumbnail")
@AllArgsConstructor
@NoArgsConstructor
public class Thumbnail {

	@JsonIgnore
	@Id
	private Long id;
	
	@NotNull
	@Column(length = 200, name = "path_file")
	private String path;
	
	@NotNull
	@Column(length = 5)
	private String extension;

}
