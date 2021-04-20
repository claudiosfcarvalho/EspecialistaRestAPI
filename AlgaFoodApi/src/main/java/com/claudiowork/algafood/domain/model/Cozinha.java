package com.claudiowork.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	long id;
	
	//@JsonIgnore //não mostra o campo no retorno
	//@JsonProperty(value = "titulo") //define o nome do campo quando for serializado
	@Column(nullable = false)
	String nome;
	
	// vincula o restaurante com a cozinha 
	// aqui é uma forma de serializacao bidirecional
	// desta forma usamos o JsonIgnore para evitar serializacao circular
	// e entrar num loop infinito
	@JsonIgnore
	@OneToMany(mappedBy = "cozinha" )
	private List<Restaurante> restaurantes = new ArrayList<>();
}
