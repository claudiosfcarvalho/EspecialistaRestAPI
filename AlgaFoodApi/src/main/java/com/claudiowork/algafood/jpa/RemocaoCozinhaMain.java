package com.claudiowork.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Cozinha;

public class RemocaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadCozinha = context.getBean(CadastroCozinha.class);
		Cozinha c1 = new Cozinha();
		c1.setId(1L);
		System.out.printf("Cozinha com o id %d será removida .", c1.getId());
		cadCozinha.remover(c1);
	}

}
