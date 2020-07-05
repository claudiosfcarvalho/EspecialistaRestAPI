package com.claudiowork.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;

import com.claudiowork.algafood.di.modelo.Cliente;

public class AtivacaoClienteService {

	@Autowired
	private Notificador notificador;

//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//		System.out.println("instancia: AtivacaoClienteService");
//	}

//	public AtivacaoClienteService(String qualquer) {
//		
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
}
