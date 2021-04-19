package com.claudiowork.algafood.api.controller;

import static com.claudiowork.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.claudiowork.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.repository.CozinhaRepository;
import com.claudiowork.algafood.domain.repository.RestauranteRepository;

@RestController
public class TestController {
//	// apenas estudo

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping("/consultaTodosPorNome")
	public List<Cozinha> cozinhasPorNomeEstudo(@RequestParam("nome") String nome) {
		return cozinhaRepository.findAllByNome(nome);
	}

	@GetMapping("/consultaTodosPorNomeParcial")
	public List<Cozinha> cozinhasPorNomeParcialEstudo(@RequestParam("nome") String nome) {
		return cozinhaRepository.findAllByNomeContaining(nome);
	}

	@GetMapping("/consultaPorNome")
	public Optional<Cozinha> cozinhaPorNomeEstudo(@RequestParam("nome") String nome) {
		return cozinhaRepository.findByNome(nome);
	}

	@GetMapping("/consultaRestaurantePorTaxa")
	public List<Restaurante> restaurantesPorFaixaTaxaEstudo(@RequestParam("taxaInicial") BigDecimal taxaInicial,
			@RequestParam("taxaFinal") BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}

	@GetMapping("/consultaRestaurantePorNomeECozinha")
	public List<Restaurante> restaurantePorNomeECozinhaId(@RequestParam("nome") String nome,
			@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
	}

	@GetMapping("/consultaRestaurantePorNomeFirst")
	public Optional<Restaurante> restaurantePrimeiroPorNome(@RequestParam("nome") String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
	}

	@GetMapping("/consultaRestaurantePorNomeTop2")
	public List<Restaurante> restaurantePorNomeTop2(@RequestParam("nome") String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping("/consultaRestaurantePorNomeExists")
	public Boolean restaurantePorNomeExists(@RequestParam("nome") String nome) {
		return restauranteRepository.existsByNome(nome);
	}

	@GetMapping("/consultaRestauranteCount")
	public Integer restauranteCount(@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}

	@GetMapping("/consultaPorNomeRestaurante")
	public List<Restaurante> cozinhaPorNomeRestaurante(@RequestParam("nome") String nome,
			@RequestParam("cozinhaId") Long cozinhaId) {
		return restauranteRepository.consultaPorNome(nome, cozinhaId);
	}

	@GetMapping("/consultaRestaurantePorTaxaENome")
	public List<Restaurante> restaurantesPorFaixaTaxa(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	//padrao DDD Specifications
	@GetMapping("/consultaRestauranteComFreteGratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);
	}
}
