package com.claudiowork.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	long id;
	
	String nome;
	
	@Column(name = "taxa_frete")
	BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "cozinha_id")//forçar um nome específico para coluna
	Cozinha cozinha;
}