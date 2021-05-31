package com.claudiowork.algafood.api.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class Utilitario {

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void merge(Map<String, Object> camposOrigem, T valueClass, HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			T objOrigem = (T) objectMapper.convertValue(camposOrigem, valueClass.getClass());
			camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
				// busca o campo na entidade
				Field field = ReflectionUtils.findField(valueClass.getClass(), nomePropriedade);

				// permite acessar o atributo privado do Objeto Restaurante
				field.setAccessible(true);

				//obtem o valor convertido no tipo correto. ex de integer para bigdecimal
				Object novoValor = ReflectionUtils.getField(field, objOrigem);

				ReflectionUtils.setField(field, valueClass, novoValor);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
}
