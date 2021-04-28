package com.claudiowork.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntidadeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontradaException(HttpStatus status, String reason) {
		super(status, reason);
	}

	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(HttpStatus.NOT_FOUND, mensagem);
	}

}
