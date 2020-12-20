package br.com.teste.resource.dto;

import br.com.teste.model.URLTyped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class URLTypedDTO {

	private String type;
	private String url;
	
	public URLTypedDTO(URLTyped urlTyped) {
		 type = urlTyped.getType();
		 url = urlTyped.getUrl();
	}
	
	
}
