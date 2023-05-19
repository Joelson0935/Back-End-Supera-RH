package br.com.banco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	@Query("select c from Conta c where c.nomeResponsavel like %?1%")
	public List<Conta> buscarContaPorNome(String nome);

}
