package br.com.banco.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.exceptions.ObjectNotFound;
import br.com.banco.model.Conta;
import br.com.banco.model.Dto.RequestConta;
import br.com.banco.repository.ContaRepository;
import br.com.banco.service.ContaService;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public String guardarDados(RequestConta request) {
		Conta conta = dtoParaEntidade(request);
		contaRepository.save(conta);
		request = entidadeParaDto(conta);
		return "Conta " + conta.getId() + " cadastrada com sucesso!";
	}

	@Override
	public RequestConta atualizarDados(Long id, RequestConta request) {
		Conta conta = buscarPorId(id);
		conta = dtoParaEntidade(request);
		conta.setId(id);
		contaRepository.save(conta);
		request = entidadeParaDto(conta);
		return request;
	}

	@Override
	public RequestConta buscarDadosPorId(Long id) {
		Conta conta = buscarPorId(id);
		RequestConta request = entidadeParaDto(conta);
		return request;
	}

	@Override
	public List<RequestConta> listarDados() {
		List<Conta> contas = contaRepository.findAll();
		List<RequestConta> listaDeContas = contas.stream().map(conta -> entidadeParaDto(conta))
				.collect(Collectors.toList());
		return listaDeContas;
	}

	@Override
	public void deletarDadosPorId(Long id) {
		Conta conta = buscarPorId(id);
		contaRepository.deleteById(conta.getId());
	}

	private RequestConta entidadeParaDto(Conta conta) {
		RequestConta request = new RequestConta(conta.getId(), conta.getNomeResponsavel(), conta.getSaldo());
		return request;
	}

	private Conta dtoParaEntidade(RequestConta request) {
		Conta conta = new Conta(request.getId(), request.getNomeResponsavel(), request.getSaldo());
		return conta;
	}

	private Conta buscarPorId(Long id) {
		Conta conta = contaRepository.findById(id).orElseThrow(() -> new ObjectNotFound("Objeto não encontrado !"));
		return conta;
	}

}
