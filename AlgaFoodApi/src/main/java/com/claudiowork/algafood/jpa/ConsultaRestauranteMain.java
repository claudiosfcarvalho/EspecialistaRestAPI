package com.claudiowork.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository cadRestaurante = context.getBean(RestauranteRepository.class);
		List<Restaurante> listRestaurante = cadRestaurante.listar();

		for (Restaurante c: listRestaurante) {
			System.out.printf("Restaurante : %d - %s - %s\n",c.getId(),c.getNome(),c.getTaxaFrete().toString());
		}
	}

}
