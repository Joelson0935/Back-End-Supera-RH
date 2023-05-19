package br.com.banco.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.banco.exceptions.ObjectNotFound;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.Dto.RequestTransferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.TransferenciaService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Override
	public RequestTransferencia guardarDados(RequestTransferencia request) {
		Conta conta = contaRepository.findById(request.getConta().getId())
				.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado!"));
		BigDecimal valor = BigDecimal.ZERO;
		if (request.getTipo().isEmpty() || request.getTipo() == null) {
			throw new IllegalArgumentException("Tipo de Operação inválida!");
		}
		if (request.getTipo().equals("DEPOSITO")) {
			valor = conta.getSaldo().add(request.getValor());
		}
		if (request.getTipo().equals("SAQUE")) {
			valor = conta.getSaldo().subtract(request.getValor());
		}
		if (request.getTipo().equals("TRANSFERENCIA")) {
			valor = conta.getSaldo().add(request.getValor());
		}
		Transferencia transferencia = new Transferencia(request.getDataTransferencia(), request.getValor(),
				request.getTipo(), request.getNomeOperadorTransacao(), conta);
		transferencia.getConta().setSaldo(valor);
		request = entidadeParaDto(transferenciaRepository.save(transferencia));
		return request;
	}

	@Override
	public RequestTransferencia atualizarDados(Long id, RequestTransferencia request) {
		Transferencia transferencia = buscarPorId(id);
		transferencia = dtoParaEntidade(request);
		transferencia.setId(id);
		request = entidadeParaDto(transferencia);
		return guardarDados(request);
	}

	@Override
	public RequestTransferencia buscarDadosPorId(Long id) {
		Transferencia transferencia = buscarPorId(id);
		RequestTransferencia request = entidadeParaDto(transferencia);
		return request;
	}

	@Override
	public List<RequestTransferencia> buscarPorNome(String nome) {
		List<Transferencia> transferencias = transferenciaRepository.buscarTransferenciaPorNome(nome);
		List<RequestTransferencia> requests = transferencias.stream()
				.map(transferencia -> entidadeParaDto(transferencia)).collect(Collectors.toList());
		return requests;
	}

	@Override
	public List<RequestTransferencia> buscarPorData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
		List<Transferencia> transferencias = transferenciaRepository.buscarTransferenciaPorData(dataFormatada);
		List<RequestTransferencia> requests = transferencias.stream()
				.map(transferencia -> entidadeParaDto(transferencia)).collect(Collectors.toList());
		return requests;
	}

	@Override
	public List<RequestTransferencia> buscarPorDataOuNome(String data, String nome) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
		List<Transferencia> transferencias = transferenciaRepository.buscarTransferenciaPorDataOuNome(dataFormatada,
				nome);
		List<RequestTransferencia> requests = transferencias.stream()
				.map(transferencia -> entidadeParaDto(transferencia)).collect(Collectors.toList());
		return requests;
	}

	@Override
	public List<RequestTransferencia> buscarTodos() {
		List<Transferencia> transferencias = transferenciaRepository.findAll();
		List<RequestTransferencia> requests = transferencias.stream()
				.map(transferencia -> entidadeParaDto(transferencia)).collect(Collectors.toList());
		return requests;
	}

	@Override
	public void deletar(Long id) {
		Transferencia transferencia = buscarPorId(id);
		transferenciaRepository.deleteById(transferencia.getId());
	}

	private RequestTransferencia entidadeParaDto(Transferencia transferencia) {
		RequestTransferencia request = new RequestTransferencia(transferencia.getId(),
				transferencia.getDataTransferencia(), transferencia.getValor(), transferencia.getTipo(),
				transferencia.getNomeOperadorTransacao(), transferencia.getConta());
		return request;
	}

	private Transferencia dtoParaEntidade(RequestTransferencia request) {
		Transferencia conta = new Transferencia(request.getId(), request.getDataTransferencia(), request.getValor(),
				request.getTipo(), request.getNomeOperadorTransacao(), request.getConta());
		return conta;
	}

	private Transferencia buscarPorId(Long id) {
		Transferencia transferencia = transferenciaRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFound("Objeto não encontrado !"));
		return transferencia;
	}

}
