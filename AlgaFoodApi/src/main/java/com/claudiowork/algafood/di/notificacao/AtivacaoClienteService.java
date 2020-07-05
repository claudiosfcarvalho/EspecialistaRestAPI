package com.claudiowork.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;

import com.claudiowork.algafood.di.modelo.Cliente;

public class AtivacaoClienteService {

	//da para adicionar a opcao required com false quando nao quiser que a instancia nao seja feita
	@Autowired//(required = false)
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
		if (notificador != null)
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
		else
			System.out.println("Não existe notificador, mas cliente foi ativado");
	}

//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
}
