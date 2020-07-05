package com.claudiowork.algafood.di.notificacao;

import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;


public class NotificadorEmail implements Notificador {

	private Boolean caixaAlta;
	private String hostServidorSmtp;
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		if (this.caixaAlta)
			mensagem = mensagem.toUpperCase();
		System.out.printf("Notificação %s através do e-mail %s usando SMTP %s: %s\n",
				nome, email, hostServidorSmtp, mensagem);
	}

	public NotificadorEmail(String hostServidorSmtp) {
		this.hostServidorSmtp = hostServidorSmtp; 
		System.out.println("instancia: NotificadorEmail");
	}

	public void setCaixaAlta(Boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}
	
	
}
