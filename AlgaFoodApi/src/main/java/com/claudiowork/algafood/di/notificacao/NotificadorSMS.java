package com.claudiowork.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@Component
public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String telefone = cliente.getTelefone();
		
		System.out.printf("Notificação %s por SMS através do telefone %s: %s\n",
				nome, telefone, mensagem);
	}

}