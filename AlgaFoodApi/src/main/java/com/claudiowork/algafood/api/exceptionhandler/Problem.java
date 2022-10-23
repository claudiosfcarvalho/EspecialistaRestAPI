package com.claudiowork.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe para implementar padrão de respostas com a RFC7807
 * @author claudio
 *
 */
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	private String userMessage;
	private LocalDateTime timestamp;
	private List<Field> fields;

	@Getter
	@Builder
	public static class Field {
		private String name;
		private String userMessage;
	}

}