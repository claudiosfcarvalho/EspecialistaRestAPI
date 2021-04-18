package com.claudiowork.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.claudiowork.algafood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}