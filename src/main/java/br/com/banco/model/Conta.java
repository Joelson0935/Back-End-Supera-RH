package br.com.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_conta")
	private Long id;

	@Column(name = "nome_responsavel")
	private String nomeResponsavel;

	@OneToMany(mappedBy = "conta")
	private List<Transferencia> transferencias = new ArrayList<Transferencia>();

	@Column(length = 10, scale = 2)
	private BigDecimal saldo;

	public Conta(String nomeResponsavel, BigDecimal saldo) {
		super();
		this.nomeResponsavel = nomeResponsavel;
		this.saldo = BigDecimal.ZERO;
	}

	public Conta(Long id, String nomeResponsavel, BigDecimal saldo) {
		super();
		this.id = id;
		this.nomeResponsavel = nomeResponsavel;
		this.saldo = BigDecimal.ZERO;
	}

}
