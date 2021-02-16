package com.claudiowork.algafood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.notificacao.NivelUrgencia;
import com.claudiowork.algafood.di.notificacao.Notificador;
import com.claudiowork.algafood.di.notificacao.TipoDoNotificador;
import com.claudiowork.algafood.di.service.ClienteAtivadoEvent;

@Component
public class NotificacaoService {

	@Autowired
	@TipoDoNotificador(NivelUrgencia.NORMAL)
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
		System.out.println("Cliente " + event.getCliente().getNome() + " agora está ativo");
	}
}
