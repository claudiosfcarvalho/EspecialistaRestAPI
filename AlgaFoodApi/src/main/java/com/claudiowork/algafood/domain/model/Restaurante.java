package com.claudiowork.algafood.domain.model;

import com.claudiowork.algafood.api.config.Groups;
import com.claudiowork.algafood.api.config.NameValidation;
import com.claudiowork.algafood.api.config.TaxaFrete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank
    @NameValidation(character = 3)
    @Column(nullable = false)
    private String nome;

    @TaxaFrete
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
    @NotNull
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn(name = "cozinha_id", nullable = false)//forçar um nome específico para coluna para fk
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private Endereco endereco;

    /**
     * Data de cadastro
     * a anotação CreationTimestamp cria automaticamente uma instância de LocalDateTime
     * e atribui a este campo no comento da criação de um novo registro sem a neccessidade
     * de implementar uma regra no service
     */
    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    /**
     * Data de atualização
     * a anotação UpdateTimestamp cria automaticamente uma instância de LocalDateTime
     * e atribui a este campo no comento da atualização de um registro existente
     * sem a neccessidade de implementar uma regra no service
     */
    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataAtualizacao;

    /**
     * joincolumns define qual coluna tem relacao a referente da classe mapeada
     * inverseJoinColumns referencia qual coluna da outra tabela tem relacao
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();
    ;
}