package com.claudiowork.algafood.api.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidationCalc implements ConstraintValidator<NameValidation, String> {

    Integer character;

    @Override
    public void initialize(NameValidation constraintAnnotation) {
        this.character = constraintAnnotation.character();
    }

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext constraintValidatorContext) {
        return valor.length() >= character;
    }
}
