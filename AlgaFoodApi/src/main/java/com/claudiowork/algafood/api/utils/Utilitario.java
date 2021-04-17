package com.claudiowork.algafood.api.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Utilitario {

	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> void merge(Map<String, Object> camposOrigem, T valueClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		T objOrigem = (T) objectMapper.convertValue(camposOrigem, valueClass.getClass());
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			// busca o campo na entidade
			Field field = ReflectionUtils.findField(valueClass.getClass() , nomePropriedade);

			// permite acessar o atributo privado do Objeto Restaurante
			field.setAccessible(true);

			//obtem o valor convertido no tipo correto. ex de integer para bigdecimal
			Object novoValor = ReflectionUtils.getField(field, objOrigem);

			ReflectionUtils.setField(field, valueClass, novoValor);
		});
	}
}
