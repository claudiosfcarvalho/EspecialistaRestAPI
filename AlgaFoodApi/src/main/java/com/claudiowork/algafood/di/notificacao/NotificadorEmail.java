package com.claudiowork.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@Profile("pro")
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorProperties prop;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		System.out.printf("Conectado no host %s:%d\n",prop.getHostServidor(),prop.getPortaServidor());
		System.out.printf("Notificação %s através do e-mail %s: %s\n",
				nome, email, mensagem);
	}

}
