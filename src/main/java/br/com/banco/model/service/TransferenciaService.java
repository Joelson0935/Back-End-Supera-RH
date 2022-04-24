package br.com.banco.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.repository.ContaRepository;
import br.com.banco.model.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private ContaRepository contaRepository;

	/**
	 * Guarda no banco de dados os dados.
	 * 
	 * @param transferencia
	 * @return um objeto do tipo transferencia.
	 */
	public Transferencia salvar(Transferencia transferencia) {
		Conta conta = contaRepository.findById(transferencia.getConta().getId())
				.orElseThrow(() -> new RuntimeException("Objeto não encontrado!"));

		BigDecimal valor = BigDecimal.ZERO;

		if (transferencia.getTipo().equals("DEPOSITO")) {
			valor = conta.getSaldo().add(transferencia.getValor());
		}
		if (transferencia.getTipo().equals("SAQUE")) {
			valor = conta.getSaldo().subtract(transferencia.getValor());
		}
		if (transferencia.getTipo().equals("TRANSFERENCIA")) {
			valor = conta.getSaldo().add(transferencia.getValor());
		}

		transferencia = new Transferencia(transferencia.getDataTransferencia(), transferencia.getValor(),
				transferencia.getTipo(), transferencia.getNomeOperadorTransicao(), conta);

		transferencia.getConta().setSaldo(valor);
		return transferenciaRepository.save(transferencia);
	}

	/**
	 * Busca um objeto no banco de dados e se encontrar o mesmo, então os dados são
	 * atualizados.
	 * 
	 * @param id
	 * @param transferencia
	 * @return Os dados atualizados.
	 */
	public Transferencia atualizar(Long id, Transferencia transferencia) {
		Transferencia t = buscarPorId(id);
		transferencia.setId(id);
		t = transferenciaRepository.save(transferencia);
		return t;
	}

	/**
	 * Busca um objeto do tipo transferencia no banco de dados.
	 * 
	 * @param id
	 * @return Devolve os dados encontrados, caso não os encontre então devolve uma
	 *         mensagem de erro.
	 */
	public Transferencia buscarPorId(Long id) {
		Transferencia transferencia = transferenciaRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Objeto não encontrado !"));
		return transferencia;
	}

	/**
	 * Busca uma lista de objetos do tipo transferencia pelo nome do operador da
	 * transação.
	 * 
	 * @param nome
	 * @return Uma lista de objetos relacionado ao nome do operador da transação
	 *         encontrado.
	 */
	public List<Transferencia> buscarPorNome(String nome) {
		List<Transferencia> nomes = transferenciaRepository.buscarTransferenciaPorNome(nome);
		return nomes;
	}

	/**
	 * Busca uma lista de objetos do tipo transferencia pela data informada.
	 * 
	 * @param data
	 * @return Uma lista de Objetos que tenham a mesma data.
	 */
	public List<Transferencia> buscarPorData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
		List<Transferencia> datas = transferenciaRepository.buscarTransferenciaPorData(dataFormatada);
		return datas;
	}

	/**
	 * Busca uma lista de objetos do tipo transferencia pela data ou nome que for
	 * informado
	 * 
	 * @param data
	 * @param nome
	 * @return Retorna a lista baseada nos parãmetros citados acima.
	 */
	public List<Transferencia> buscarPorDataOuNome(String data, String nome) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
		List<Transferencia> buscarDatasOuNomes = transferenciaRepository.buscarTransferenciaPorDataOuNome(dataFormatada,
				nome);
		return buscarDatasOuNomes;
	}

	/**
	 * Busca todos os dados no banco de dados que sejam do tipo transferencia.
	 * 
	 * @return retorna os dados encontrados no banco, caso não os encontre devolve
	 *         um array vazio.
	 */
	public List<Transferencia> buscarTodos() {
		List<Transferencia> transferencias = transferenciaRepository.findAll();
		return transferencias;
	}

	/**
	 * Busca um objeto do tipo transferencia no banco de dados e em seguida o
	 * Deleta.
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		Transferencia transferencia = buscarPorId(id);
		transferenciaRepository.delete(transferencia);
	}

}
