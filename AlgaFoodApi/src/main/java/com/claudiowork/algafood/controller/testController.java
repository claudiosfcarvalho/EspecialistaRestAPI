package com.claudiowork.algafood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.claudiowork.algafood.di.modelo.Cliente;
import com.claudiowork.algafood.di.notificacao.AtivacaoClienteService;

@Controller()
public class testController {

	@Autowired
	private AtivacaoClienteService ativacaoClienteService;
	
	public testController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		System.out.println("instancia: controller");
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente joao = new Cliente("João","joao@xyz.com","3499998888");
		ativacaoClienteService.ativar(joao);
		return "Hello!";
	}
}
