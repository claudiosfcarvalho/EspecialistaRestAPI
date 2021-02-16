package com.claudiowork.algafood.di.notificacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.claudiowork.algafood.di.modelo.Cliente;

@Service
public class AtivacaoClienteService {

	//da para adicionar a opcao required com false quando nao quiser que a instancia nao seja feita
	@Autowired//(required = false)
	//private List<Notificador> notificadores;
	//@Qualifier("email") //aqui indica para o spring usar o qualificador email ou sms(de acordo com o que é definido
	@TipoDoNotificador(NivelUrgencia.NORMAL)
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();
//		implementacao dupla por email e sms com a solução: updating the consumer to accept multiple beans
//		for (Notificador notificador: notificadores) {
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
//		}
	}


/*
 * Anotações de estudo
 * 
 * quando apresentou o erro 
 * 
 * Description:

Field notificador in com.claudiowork.algafood.di.notificacao.AtivacaoClienteService required a single bean, but 2 were found:
	- notificadorEmail: defined in file [D:\1 - Projetos\Algaworks\EspecialistaRestAPI\EspecialistaRestAPI\AlgaFoodApi\target\classes\com\claudiowork\algafood\di\notificacao\NotificadorEmail.class]
	- notificadorSMS: defined in file [D:\1 - Projetos\Algaworks\EspecialistaRestAPI\EspecialistaRestAPI\AlgaFoodApi\target\classes\com\claudiowork\algafood\di\notificacao\NotificadorSMS.class]


Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

consideramos que temos 2 beans para gerenciar assim podemos tomar varias atitudes para resolver e tratar a ambiguidade
 * */	
	
//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//		System.out.println("instancia: AtivacaoClienteService");
//	}

//	public AtivacaoClienteService(String qualquer) {
//		
//	}	
//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
}
