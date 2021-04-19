package com.claudiowork.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.claudiowork.algafood.AlgaFoodApiApplication;
import com.claudiowork.algafood.domain.model.Estado;
import com.claudiowork.algafood.domain.repository.EstadoRepository;

public class ConsultaEstadoMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgaFoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        
        EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
        
        List<Estado> todosEstados = estadoRepository.listar();
        
        for (Estado estado : todosEstados) {
            System.out.printf("%s", estado.getNome());
        }
    }
    
}
