package com.claudiowork.algafood.api.config;

import com.claudiowork.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.claudiowork.algafood.domain.model.Cozinha;
import com.claudiowork.algafood.domain.model.Restaurante;
import com.claudiowork.algafood.domain.service.CadastroCidadeService;
import com.claudiowork.algafood.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Locale;

@Component
public class RestauranteValidationImpl implements ConstraintValidator<RestauranteValidation, Restaurante> {

    @Autowired
    private CadastroCozinhaService cozinhaService;
    boolean taxaFreteObrigatoria = false;
    String[] tipoCozinhaNaoValida;

    @Override
    public void initialize(RestauranteValidation constraintAnnotation) {
        this.taxaFreteObrigatoria = constraintAnnotation.taxaFreteObrigatoria();
        this.tipoCozinhaNaoValida = constraintAnnotation.naoValidar();
        System.out.println("Valida taxa Frete = " + this.taxaFreteObrigatoria);
        System.out.println("Tipo de cozinhas que não valida = " + Arrays.stream(this.tipoCozinhaNaoValida).toList());
    }

    @Override
    public boolean isValid(Restaurante obj, ConstraintValidatorContext constraintValidatorContext) {
        var validador = true;
        Cozinha cozinha;
        BigDecimal taxaFrete;
        try {
            cozinha = (Cozinha) BeanUtils.getPropertyDescriptor(obj.getClass(),"Cozinha")
                    .getReadMethod().invoke(obj);
            taxaFrete = (BigDecimal) BeanUtils.getPropertyDescriptor(obj.getClass(),"taxaFrete")
                    .getReadMethod().invoke(obj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new ValidationException(e);
        }
        if (cozinhaService != null) {
            try {
                Cozinha cozinhaConsultada = cozinhaService.buscarOuFalhar(cozinha.getId());
                if (this.taxaFreteObrigatoria) {
                    validador = false;
                    if (taxaFrete.doubleValue() > 0.0) {
                        validador = true;
                    } else if (this.tipoCozinhaNaoValida.length > 0) {
                        validador = Arrays.stream(this.tipoCozinhaNaoValida)
                                .anyMatch(nome -> cozinhaConsultada.getNome().toLowerCase(Locale.ROOT)
                                        .contains(nome.toLowerCase(Locale.ROOT)));
                    }
                }
            } catch (CozinhaNaoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Validador="+validador);
        return validador;
    }
}
