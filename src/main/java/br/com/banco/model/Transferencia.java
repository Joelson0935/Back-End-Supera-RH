package br.com.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Transferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataTransferencia;

	@Column(length = 20, scale = 2)
	private BigDecimal valor;
 
	private String tipo;

	private String nomeOperadorTransacao;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade = CascadeType.ALL)
	private Conta conta;

	public Transferencia() {
		super();
	}

	public Transferencia(Long id, LocalDateTime dataTransferencia, BigDecimal valor, String tipo,
			String nomeOperadorTransacao, Conta conta) {
		super();
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

	public Transferencia(LocalDateTime dataTransferencia, BigDecimal valor, String tipo, String nomeOperadorTransacao,
			Conta conta) {
		super();
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDateTime dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeOperadorTransicao() {
		return nomeOperadorTransacao;
	}

	public void setNomeOperadorTransicao(String nomeOperadorTransacao) {
		this.nomeOperadorTransacao = nomeOperadorTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transferencia other = (Transferencia) obj;
		return Objects.equals(id, other.id);
	}

}
