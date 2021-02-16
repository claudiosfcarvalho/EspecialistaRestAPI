package com.claudiowork.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.claudiowork.algafood.di.modelo.Cliente;

@Component
public class AtivacaoClienteService {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
	}

	/*
	 * Anotações de estudo
	 * 
	 * quando apresentou o erro
	 * 
	 * Description:
	 * 
	 * Field notificador in
	 * com.claudiowork.algafood.di.notificacao.AtivacaoClienteService required a
	 * single bean, but 2 were found: - notificadorEmail: defined in file [D:\1 -
	 * Projetos\Algaworks\EspecialistaRestAPI\EspecialistaRestAPI\AlgaFoodApi\target
	 * \classes\com\claudiowork\algafood\di\notificacao\NotificadorEmail.class] -
	 * notificadorSMS: defined in file [D:\1 -
	 * Projetos\Algaworks\EspecialistaRestAPI\EspecialistaRestAPI\AlgaFoodApi\target
	 * \classes\com\claudiowork\algafood\di\notificacao\NotificadorSMS.class]
	 * 
	 * 
	 * Action:
	 * 
	 * Consider marking one of the beans as @Primary, updating the consumer to
	 * accept multiple beans, or using @Qualifier to identify the bean that should
	 * be consumed
	 * 
	 * consideramos que temos 2 beans para gerenciar assim podemos tomar varias
	 * atitudes para resolver e tratar a ambiguidade
	 */

//	@Autowired
//	@TipoDoNotificador(NivelUrgencia.NORMAL)
//	private Notificador notificador;
	
	
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

//	//Call back - método 1
//	//da forma abaixo é possível obter o momento após a construção da classe
//	@PostConstruct
//	public void init() {
//		System.out.println("init");
//	}
//
//	//da forma abaixo é poss[ivel obter o momento pré destruição da classe
//	@PreDestroy
//	public void destroy() {
//		System.out.println("destroy");
//	}
//	 //Callback - método 2
//	 //é possível usar estes metodos sem estas anotações quando o bean é definido na
//	 //classe
//	 //de configuração, desta forma ficaria assim:
//	 //@Configuration
//	 //public classe Config {
//	 //   @Bean(initMethod = "init", destroyMethod = "destroy")
//	 //   public AtivacaoClienteService ativacaoClienteService(){
//	 //     return new AtivacaoClienteService();
//	 //   }
//	 //}
}
