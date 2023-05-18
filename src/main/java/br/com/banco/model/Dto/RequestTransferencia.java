package br.com.banco.model.Dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.banco.model.Conta;
import lombok.Data;

@Data
public class RequestTransferencia implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataTransferencia;
	private BigDecimal valor;
	private String tipo;
	private String nomeOperadorTransacao;
	private Conta conta;

	public RequestTransferencia() {
		super();
	}

	public RequestTransferencia(Long id, LocalDateTime dataTransferencia, BigDecimal valor, String tipo,
			String nomeOperadorTransacao, Conta conta) {
		super();
		this.id = id;
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

	public RequestTransferencia(LocalDateTime dataTransferencia, BigDecimal valor, String tipo,
			String nomeOperadorTransacao, Conta conta) {
		super();
		this.dataTransferencia = dataTransferencia;
		this.valor = valor;
		this.tipo = tipo;
		this.nomeOperadorTransacao = nomeOperadorTransacao;
		this.conta = conta;
	}

}
