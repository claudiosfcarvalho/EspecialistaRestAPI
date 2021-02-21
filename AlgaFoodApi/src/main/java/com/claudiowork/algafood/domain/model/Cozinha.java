package com.claudiowork.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity //@Table(name = "nome da tabela") -> se quiser colocar nome diferente da classe
@Data
public class Cozinha {

	@Id
	long id;
	
	@Column//(name = "nome_cozinha")
	String nome;
}
