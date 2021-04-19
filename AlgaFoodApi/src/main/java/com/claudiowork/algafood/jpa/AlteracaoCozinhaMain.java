package com.claudiowork.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;

public class AlteracaoCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadCozinha = context.getBean(CozinhaRepository.class);
		Cozinha c1 = new Cozinha();
		c1.setId(1L);
		c1.setNome("Brasileira");
		
		c1 = cadCozinha.salvar(c1);
		System.out.printf("Cozinha %s adicionada com o id %d.",c1.getNome(), c1.getId());
	}

}
