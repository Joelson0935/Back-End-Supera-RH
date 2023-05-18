package br.com.banco.model.service;

import java.util.List;

import br.com.banco.model.Dto.RequestTransferencia;

public interface TransferenciaService {

	RequestTransferencia guardarDados(RequestTransferencia request);

	RequestTransferencia atualizarDados(Long id, RequestTransferencia request);

	RequestTransferencia buscarDadosPorId(Long id);

	List<RequestTransferencia> buscarPorNome(String nome);

	List<RequestTransferencia> buscarPorData(String data);

	List<RequestTransferencia> buscarPorDataOuNome(String data, String nome);

	List<RequestTransferencia> buscarTodos();

	void deletar(Long id);

}
