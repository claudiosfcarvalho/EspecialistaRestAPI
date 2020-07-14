package com.claudiowork.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.NORMAL)
//@Primary //utilizado para desambiguar e coloca este como primeiro bean
//@Qualifier("email")
@Component
public class NotificadorEmail implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		
		System.out.printf("Notificação %s através do e-mail %s: %s\n",
				nome, email, mensagem);
	}

}
