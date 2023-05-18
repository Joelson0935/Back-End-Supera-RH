package br.com.banco.model.Dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestConta implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nomeResponsavel;
	private BigDecimal saldo;

}
