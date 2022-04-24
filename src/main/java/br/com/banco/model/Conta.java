package br.com.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	private BigDecimal saldo = BigDecimal.ZERO;

	public Conta() {
		super();
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeResponsavel() {
		return nomeResponsavel;
	}

	public void setNomeResponsavel(String nomeResponsavel) {
		this.nomeResponsavel = nomeResponsavel;
	}

	public List<Transferencia> getTransferencias() {
		return transferencias;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
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
		Conta other = (Conta) obj;
		return Objects.equals(id, other.id);
	}

}
