package com.claudiowork.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

public class AtivacaoClienteService {

	private Notificador notificador;

	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		System.out.println("instancia: AtivacaoClienteService");
	}


	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
