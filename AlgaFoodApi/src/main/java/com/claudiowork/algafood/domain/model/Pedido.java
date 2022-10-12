package com.claudiowork.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataConfirmacao;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataCancelamento;
	
	@Column(columnDefinition = "datetime")
	private LocalDateTime dataEntrega;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "usuario_cliente_id")
	private Usuario usuario;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	
}
