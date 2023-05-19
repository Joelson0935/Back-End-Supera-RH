package br.com.banco.model.Dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.banco.model.Conta;
import lombok.Data;

@Data
public class RequestTransferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTransferencia;
	private BigDecimal valor;
	private String tipo;
	private String nomeOperadorTransacao;
	@JsonProperty(access = Access.WRITE_ONLY)
	private Conta conta;

	public RequestTransferencia() {
		super();
	}

	public RequestTransferencia(Long id, LocalDate dataTransferencia, BigDecimal valor, String tipo,
			String nomeOperadorTransacao, Conta conta) {
		super();
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

	public RequestTransferencia(LocalDate dataTransferencia, BigDecimal valor, String tipo,
			String nomeOperadorTransacao, Conta conta) {
		super();
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

}
