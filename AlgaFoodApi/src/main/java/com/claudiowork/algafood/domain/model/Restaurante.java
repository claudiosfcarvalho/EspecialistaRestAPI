package com.claudiowork.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Restaurante {

	@Id
	long id;
	String nome;
	@Column(name = "taxa_frete")
	BigDecimal taxaFrete;
}
