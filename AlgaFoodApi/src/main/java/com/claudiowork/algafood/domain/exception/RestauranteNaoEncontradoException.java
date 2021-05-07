package com.claudiowork.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long id) {
		this(String.format("Restaurante de código %d não encontrado", id));
	}
	

}
