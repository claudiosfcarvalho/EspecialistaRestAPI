package com.claudiowork.algafood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.claudiowork.algafood.di.notificacao.AtivacaoClienteService;
import com.claudiowork.algafood.di.notificacao.Notificador;

@Configuration
public class ServiceConfig {

	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}
}
