package com.claudiowork.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.claudiowork.algafood.domain.model.Restaurante;

/**
 * Gerador de Specification padrao DDD
 * @author claudio
 *
 */
public class RestauranteSpecs {

	public static Specification<Restaurante> comFreteGratis() {
		//retornando instancia do specification implementando o Specification.toPredicate
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome)  {
		//retornando instancia do specification implementando o Specification.toPredicate
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
}
