package com.claudiowork.algafood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.claudiowork.algafood.di.notificacao.Notificador;
import com.claudiowork.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class ServiceConfig {
/*
 * quando for necessario criar mais de uma classe de configuration
 * poder injetar dependencia de outro bean feito em outra configuração
 * ou em qualquer outra anotação de bean realizado na app como @Component por exemplo
 * no casso abaixo temos por exemplo o Notificador que é gerenciado em outra classe
 * pelo spring
	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);
	}
	
*/
}
