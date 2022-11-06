package com.claudiowork.algafood.domain.model;

import com.claudiowork.algafood.api.config.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@NotNull(groups = Groups.CozinhaId.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	/**
	 * 
	 * Campo restaurantes - relacionado com Restaurantes.cozinhaId
	 * vincula o restaurante com a cozinha aqui é uma forma de serializacao
	 * bidirecional desta forma usamos o JsonIgnore para evitar serializacao
	 * circular e entrar num loop infinito essa associacao nao gera coluna na tabela
	 * com o uso do JsonIgnore
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
}
