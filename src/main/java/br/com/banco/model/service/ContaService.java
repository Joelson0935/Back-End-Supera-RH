package br.com.banco.model.service;

import java.util.List;

import br.com.banco.model.Dto.RequestConta;

public interface ContaService {

	String guardarDados(RequestConta request);
	
	RequestConta atualizarDados(Long id, RequestConta request);
	
	RequestConta buscarDadosPorId(Long id);
	
	List<RequestConta> listarDados();
	
	void deletarDadosPorId(Long id);
	
}
