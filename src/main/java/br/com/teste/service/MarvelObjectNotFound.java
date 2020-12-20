package br.com.teste.service;

public class MarvelObjectNotFound extends RuntimeException {

	private static final long serialVersionUID = -2936026315276642192L;

	public MarvelObjectNotFound() {
		super();
	}

	public MarvelObjectNotFound(String message) {
		super(message);
	}
	

}
