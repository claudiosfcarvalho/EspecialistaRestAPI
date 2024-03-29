package com.claudiowork.algafood.api.exceptionhandler;

import com.claudiowork.algafood.domain.exception.EntidadeEmUsoException;
import com.claudiowork.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.claudiowork.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.flywaydb.core.internal.util.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String MSG_ERRO_GENERICO_USUARIO_FINAL = "Ocorreu um erro interno inesperado do sistema. Tente novamente e se o problema persistir" +
			" entre em contato com o administrador do sistema ";

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Throwable rootCause = ExceptionUtils.getRootCause(e);

		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
		}

		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe";
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException e, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		
		String path = joinPath(e.getPath());
		
		String detail = String.format(
				"A propriedade '%s' não existe. Corrija ou remova essa propriedade e tente novamente.",
				path);
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(e, problem, headers, status, request);
	}
	
	private String joinPath(List<Reference> list) {
		return list.stream().map(ref-> ref.getFieldName()).collect(Collectors.joining("."));
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String path = joinPath(e.getPath());
		String detail = String.format(
				"A propriedade '%s' recebeu o valor '%s',"
						+ " que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
				path, e.getValue(), e.getTargetType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, headers, status, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleExceptionGeneral(Exception e, WebRequest request) {
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		String detail = String.format(
				MSG_ERRO_GENERICO_USUARIO_FINAL
				);
		Problem problem = createProblemBuilder(HttpStatus.INTERNAL_SERVER_ERROR, problemType, detail).build();
		return handleExceptionInternal(e, problem, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException e, HttpHeaders headers,
																 HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String path = e.getRequestURL();
		String detail = String.format(
				"O recurso '%s' que você tentou acessar é inexistente",
				path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
														HttpStatus status, WebRequest request) {
		if (ex instanceof MethodArgumentTypeMismatchException)	{
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		return super.handleTypeMismatch(ex,headers,status,request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s'," +
				" que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s. ", ex.getName(), ex.getValue(), ex.getRequiredType().getName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = e.getMessage();
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);

	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (body == null || body instanceof String) {
			body = Problem.builder().title(body == null ? status.getReasonPhrase() : (String) body)
					.status(status.value()).build();
		}

		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle())
				.detail(detail).userMessage(MSG_ERRO_GENERICO_USUARIO_FINAL)
				.timestamp(LocalDateTime.now());
	}

}
