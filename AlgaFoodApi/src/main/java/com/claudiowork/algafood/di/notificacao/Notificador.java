package com.claudiowork.algafood.di.notificacao;

import com.claudiowork.algafood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}