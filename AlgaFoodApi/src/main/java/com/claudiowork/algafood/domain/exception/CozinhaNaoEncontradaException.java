package com.claudiowork.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	
	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		this(String.format("Cozinha de código %d não encontrada", id));
	}
	

}
