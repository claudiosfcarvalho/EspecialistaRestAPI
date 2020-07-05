package com.claudiowork.algafood.di.modelo;

import lombok.Data;

@Data
public  class  Cliente {

	private String nome;
	private String email;
	private String telefone;
	private Boolean ativo = Boolean.FALSE;
	
	public Cliente(String nome, String email, String telefone) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public void ativar() {
		this.ativo = Boolean.TRUE;	
	}
	
	public Boolean isAtivo() {
		return ativo;
	}
}
