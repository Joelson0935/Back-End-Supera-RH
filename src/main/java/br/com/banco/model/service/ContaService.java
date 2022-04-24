package br.com.banco.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.model.Conta;
import br.com.banco.model.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;

	/**
	 * Guarda no banco de dados os dados.
	 * 
	 * @param conta
	 * @return um objeto do tipo conta.
	 */
	public Conta salvar(Conta conta) {
		return contaRepository.save(conta);
	}

	/**
	 * Busca um objeto no banco de dados e se encontrar o mesmo, então os dados são
	 * atualizados.
	 * 
	 * @param id
	 * @param conta
	 * @return Os dados atualizados.
	 */
	public Conta atualizar(Long id, Conta conta) {
		Conta c = buscarPorId(id);
		conta.setId(id);
		c = contaRepository.save(conta);
		return c;
	}

	/**
	 * Busca um objeto do tipo conta no banco de dados.
	 * 
	 * @param id
	 * @return Devolve os dados encontrados, caso não os encontre então devolve uma
	 *         mensagem de erro.
	 */
	public Conta buscarPorId(Long id) {
		Conta conta = contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Objeto não encontrado !"));
		return conta;
	}

	/**
	 * Busca todos os dados no banco de dados que sejam do tipo conta.
	 * 
	 * @return retorna os dados encontrados no banco, caso não os encontre devolve
	 *         um array vazio.
	 */
	public List<Conta> buscarTodos() {
		List<Conta> contas = contaRepository.findAll();
		return contas;
	}

	/**
	 * Busca um objeto do tipo conta no banco de dados e em seguida o Deleta.
	 * 
	 * @param id
	 */
	public void deletar(Long id) {
		Conta conta = buscarPorId(id);
		contaRepository.delete(conta);
	}

}
