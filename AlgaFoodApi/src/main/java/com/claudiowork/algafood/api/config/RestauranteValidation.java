package com.claudiowork.algafood.api.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RestauranteValidationImpl.class})
public @interface RestauranteValidation {

    String message() default "{RestauranteValidation.invalida}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean taxaFreteObrigatoria() default false;
    String[] naoValidar() default {};

}
