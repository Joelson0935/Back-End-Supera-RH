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

import br.com.banco.model.Transferencia;
import br.com.banco.model.service.TransferenciaService;

@RestController
@RequestMapping("/Transferencias")
@CrossOrigin("*")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;

	@PostMapping("/Salvar")
	public ResponseEntity<Transferencia> salvar(@RequestBody Transferencia transferencia) {
		Transferencia t = transferenciaService.salvar(transferencia);
		return new ResponseEntity<Transferencia>(t, HttpStatus.CREATED);
	}

	@PutMapping("/Atualizar")
	public ResponseEntity<Transferencia> atualizar(@RequestParam(name = "id") Long id,
			@RequestBody Transferencia transferencia) {
		Transferencia t = transferenciaService.buscarPorId(id);
		if (t == null) {
			return ResponseEntity.notFound().build();
		}
		transferenciaService.atualizar(id, transferencia);
		return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
	}

	@GetMapping("/BuscarPorId")
	public ResponseEntity<Transferencia> buscarPorId(@RequestParam(name = "id") Long id) {
		Transferencia transferencia = transferenciaService.buscarPorId(id);
		if (transferencia == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Transferencia>(transferencia, HttpStatus.OK);
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<Transferencia>> buscarTodos() {
		List<Transferencia> transferencia = transferenciaService.buscarTodos();
		return ResponseEntity.ok(transferencia);
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<Transferencia>> buscarPorNome(@RequestParam(name = "nome") String nome) {
		List<Transferencia> transferencia = transferenciaService.buscarPorNome(nome);
		return ResponseEntity.ok(transferencia);
	}

	@GetMapping("/BuscarPorData")
	public ResponseEntity<List<Transferencia>> buscarPorData(@RequestParam(name = "data") String data) {
		List<Transferencia> transferencia = transferenciaService.buscarPorData(data);
		return ResponseEntity.ok(transferencia);
	}

	@GetMapping("/BuscarPorDataOuNome")
	public ResponseEntity<List<Transferencia>> buscarPorDataOuNome(@Param(value = "data") String data,
			@Param(value = "nome") String nome) {
		List<Transferencia> transferencia = transferenciaService.buscarPorDataOuNome(data, nome);
		return ResponseEntity.ok(transferencia);
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletar(@RequestParam(name = "id") Long id) {
		Transferencia transferencia = transferenciaService.buscarPorId(id);
		if (transferencia == null) {
			return ResponseEntity.notFound().build();
		}
		transferenciaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
