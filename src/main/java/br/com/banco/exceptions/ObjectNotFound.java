package br.com.banco.exceptions;

public class ObjectNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ObjectNotFound(String message) {
		super(message);
	}

}
