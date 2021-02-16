package com.claudiowork.algafood.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmailMock implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		
		System.out.printf("|| Mock || Notificação %s através do e-mail %s: %s\n",
				nome, email, mensagem);
	}

}
