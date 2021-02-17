package com.claudiowork.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

	/**
	 * Host do Servidor de email
	 */
	private String hostServidor;
	/**
	 * Porta do Servidor de email
	 */
	private Integer portaServidor;
}
