package com.claudiowork.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@Profile("pro")
@TipoDoNotificador(NivelUrgencia.NORMAL)
//@Primary //utilizado para desambiguar e coloca este como primeiro bean
//@Qualifier("email")
@Component
public class NotificadorEmail implements Notificador {
	@Value("${notificador.email.host-servidor}")
	String host;
	@Value("${notificador.email.porta-servidor}")
	Integer porta;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		System.out.printf("Conectado no host %s:%d\n",host,porta);
		System.out.printf("Notificação %s através do e-mail %s: %s\n",
				nome, email, mensagem);
	}

}
