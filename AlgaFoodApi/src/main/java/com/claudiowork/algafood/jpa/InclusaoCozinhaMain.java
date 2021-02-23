package com.claudiowork.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadCozinha = context.getBean(CadastroCozinha.class);
		Cozinha c1 = new Cozinha();
		c1.setNome("Brasileira");
		
		c1 = cadCozinha.adicionar(c1);
		System.out.printf("Cozinha %s adicionada com o id %d.",c1.getNome(), c1.getId());
	}

}
