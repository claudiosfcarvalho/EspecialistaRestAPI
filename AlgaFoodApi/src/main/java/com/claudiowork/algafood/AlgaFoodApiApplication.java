package com.claudiowork.algafood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.claudiowork.algafood.infrastructure.repository.CustomJpaRepositoryImpl;

/**
 * Classe principal
 * a anotacao EnableJpaRepositories permite que o Spring JPA consiga ver a implementacao
 * customizada de um repositorio, substituindo a padrao
 * @author claudio
 *
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgaFoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgaFoodApiApplication.class, args);
	}

}
