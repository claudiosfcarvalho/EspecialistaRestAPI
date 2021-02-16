package com.claudiowork.algafood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.claudiowork.algafood.di.notificacao.NotificadorEmail;
import com.claudiowork.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class AlgaConfig {
/*
 * 
 * aqui é um exemplo de gerar um bean personalizado
 * quando usamos o @Component há apenas uma instaciação padrao
 * quando precisamos fazer mais coisas que o spring nao consegue fazer automatico
 * podemos utilizar a anotação bean na instancia.
 * para isso criar uma classe com a anotação configuration
 * todos os metodos que geram uma instancia anota-se com @Bean
 * desta forma temos uma instancia mais complexa e personalizada de acordo
 * com a necessidade que temos
	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
		notificador.setCaixaAlta(Boolean.TRUE);
		return notificador;
	}
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService(notificadorEmail());
	}
*/
}

