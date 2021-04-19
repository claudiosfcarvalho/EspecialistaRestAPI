package com.claudiowork.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cadCozinha = context.getBean(CozinhaRepository.class);
		List<Cozinha> listCozinha = cadCozinha.listar();
		
		for (Cozinha c: listCozinha) {
			System.out.printf("Cozinha : %d - %s\n",c.getId(),c.getNome());
		}
	}

}
