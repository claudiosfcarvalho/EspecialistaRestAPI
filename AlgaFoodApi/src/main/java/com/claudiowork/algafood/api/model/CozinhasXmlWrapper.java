package com.claudiowork.algafood.api.model;

import java.util.List;

import com.claudiowork.algafood.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cozinhas")
@Data
public class CozinhasXmlWrapper {

	/**
	 * Responsável por empacotar uma lista de cozinhas
	 */
	
	@JsonProperty("cozinha") //esta anotação sobrepõe o valor do json dentro de Cozinha
	@JacksonXmlElementWrapper(useWrapping = false) //com essa anotação desabilita o wrapping, assim não duplica
	//@JacksonXmlProperty(localName = "listagem_cozinha") //podemos usar também esta anotação
	@NonNull
	private List<Cozinha> cozinhas;
	
}
