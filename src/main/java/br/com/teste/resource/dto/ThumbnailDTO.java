package br.com.teste.resource.dto;

import javax.persistence.Column;

import org.springframework.beans.BeanUtils;
import org.springframework.context.support.BeanDefinitionDsl.BeanSupplierContext;

import br.com.teste.model.Thumbnail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ThumbnailDTO {

	@Column(length = 200)
	private String path;
	
	@Column(length = 5)
	private String extension;
	
	public ThumbnailDTO (Thumbnail thumbnai) {
		this.path = thumbnai.getPath();
		this.extension=thumbnai.getExtension();
	}
}
