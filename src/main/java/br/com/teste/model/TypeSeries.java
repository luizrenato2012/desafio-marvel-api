package br.com.teste.model;

public enum TypeSeries {
	COLLECTION ("collection");
	
	private String description;

	private TypeSeries(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
