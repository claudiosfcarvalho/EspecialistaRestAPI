package com.claudiowork.algafood.infrastructure.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.claudiowork.algafood.domain.model.Restaurante;

import lombok.AllArgsConstructor;

/**
 * RestauranteComFreteGratisSpec - padrao DDD Specifications
 * @author claudio
 *
 */
@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	
	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("nome"), "%" + nome + "%");
	}

}
