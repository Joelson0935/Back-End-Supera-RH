package br.com.banco.exceptions;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Error implements Serializable {
	private static final long serialVersionUID = 1L;

	private String error;
	private Integer code;
	private LocalDate date;
	private String path;
	private List<Fields> fields = new ArrayList<>();

	public void setFields(String name, String error) {
		fields.add(new Fields(name, error));
	}

	public Error(String error, Integer code, LocalDate date, String path) {
		super();
		this.error = error;
		this.code = code;
		this.date = date;
		this.path = path;
	}

}