package com.claudiowork.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.service.ClienteAtivadoEvent;

@Component
public class EmissaoNotaFiscalService {

	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		System.out.println("Emitindo Nota Fiscal para o Cliente " + event.getCliente().getNome());
	}
}
