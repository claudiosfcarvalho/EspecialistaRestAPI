package com.claudiowork.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

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

}
