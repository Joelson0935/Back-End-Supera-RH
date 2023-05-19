package br.com.banco.exceptions;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Fields implements Serializable {
	private static final long serialVersionUID = 1L;

	private String fieldName;
	private String fieldError;

}
