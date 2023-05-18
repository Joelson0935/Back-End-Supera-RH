package br.com.banco.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Dto.RequestTransferencia;
import br.com.banco.model.service.TransferenciaService;

@RestController
@RequestMapping("/Transferencias")
@CrossOrigin("*")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;

	@PostMapping("/Salvar")
	public ResponseEntity<RequestTransferencia> salvar(@RequestBody RequestTransferencia request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.guardarDados(request));
	}

	@PutMapping("/Atualizar")
	public ResponseEntity<RequestTransferencia> atualizar(@RequestParam(name = "id") Long id,
			@RequestBody RequestTransferencia request) {
		return ResponseEntity.ok(transferenciaService.atualizarDados(id, request));
	}

	@GetMapping("/BuscarPorId")
	public ResponseEntity<RequestTransferencia> buscarPorId(@RequestParam(name = "id") Long id) {
		return ResponseEntity.ok(transferenciaService.buscarDadosPorId(id));
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<RequestTransferencia>> buscarTodos() {
		return ResponseEntity.ok(transferenciaService.buscarTodos());
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<RequestTransferencia>> buscarPorNome(@RequestParam(name = "nome") String nome) {
		return ResponseEntity.ok(transferenciaService.buscarPorNome(nome));
	}

	@GetMapping("/BuscarPorData")
	public ResponseEntity<List<RequestTransferencia>> buscarPorData(@RequestParam(name = "data") String data) {
		return ResponseEntity.ok(transferenciaService.buscarPorData(data));
	}

	@GetMapping("/BuscarPorDataOuNome")
	public ResponseEntity<List<RequestTransferencia>> buscarPorDataOuNome(@Param(value = "data") String data,
			@Param(value = "nome") String nome) {
		return ResponseEntity.ok(transferenciaService.buscarPorDataOuNome(data, nome));
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletar(@RequestParam(name = "id") Long id) {
		transferenciaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
