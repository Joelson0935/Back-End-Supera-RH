package br.com.banco.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.banco.model.Dto.RequestConta;
import br.com.banco.model.service.ContaService;

@RestController
@RequestMapping("/Contas")
@CrossOrigin("*")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@PostMapping("/Salvar")
	public ResponseEntity<String> guardarDados(@RequestBody RequestConta request) {
		return ResponseEntity.status(HttpStatus.CREATED).body(contaService.guardarDados(request));
	}

	@PutMapping("/Atualizar")
	public ResponseEntity<RequestConta> atualizarDados(@RequestParam(name = "id") Long id,
			@RequestBody RequestConta request) {
		return ResponseEntity.ok(contaService.atualizarDados(id, request));
	}

	@GetMapping("/BuscarPorId")
	public ResponseEntity<RequestConta> buscarPorId(@RequestParam(name = "id") Long id) {
		return new ResponseEntity<RequestConta>(contaService.buscarDadosPorId(id), HttpStatus.OK);
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<RequestConta>> buscarTodos() {
		return ResponseEntity.ok(contaService.listarDados());
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletar(@RequestParam(name = "id") Long id) {
		contaService.deletarDadosPorId(id);
		return ResponseEntity.noContent().build();
	}

}
