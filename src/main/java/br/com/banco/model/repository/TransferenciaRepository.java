package br.com.banco.model.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.banco.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("select t from Transferencia t where t.nomeOperadorTransacao like %?1%")
	public List<Transferencia> buscarTransferenciaPorNome(String nome);

	@Query("select t from Transferencia t where t.dataTransferencia = ?1")
	public List<Transferencia> buscarTransferenciaPorData(LocalDateTime dataTransferencia);

	@Query("select t from Transferencia t where t.dataTransferencia = :dataTransferencia or t.nomeOperadorTransacao = :nomeOperadorTransacao")
	public List<Transferencia> buscarTransferenciaPorDataOuNome(LocalDateTime dataTransferencia,
			String nomeOperadorTransacao);
}
