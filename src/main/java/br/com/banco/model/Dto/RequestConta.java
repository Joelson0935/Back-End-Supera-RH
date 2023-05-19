package br.com.banco.model.Dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
	@JsonFormat(shape = Shape.STRING)
	private BigDecimal saldo = BigDecimal.ZERO;

}
