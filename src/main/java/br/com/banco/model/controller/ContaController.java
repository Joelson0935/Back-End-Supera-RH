package br.com.banco.model.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.banco.model.Conta;
import br.com.banco.model.Dto.ContaDto;
import br.com.banco.model.service.ContaService;

@RestController
@RequestMapping("/Contas")
@CrossOrigin("*")
public class ContaController {

	@Autowired
	private ContaService contaService;

	@PostMapping("/Salvar")
	public ResponseEntity<Conta> salvar(@RequestBody Conta conta) {
		Conta c = contaService.salvar(conta);
		return new ResponseEntity<Conta>(c, HttpStatus.CREATED);
	}

	@PutMapping("/Atualizar")
	public ResponseEntity<Conta> atualizar(@RequestParam(name = "id") Long id, @RequestBody Conta conta) {
		Conta c = contaService.buscarPorId(id);
		if (c == null) {
			return ResponseEntity.notFound().build();
		}
		c = contaService.atualizar(id, conta);
		return new ResponseEntity<Conta>(c, HttpStatus.OK);
	}

	@GetMapping("/BuscarPorId")
	public ResponseEntity<Conta> buscarPorId(@RequestParam(name = "id") Long id) {
		Conta conta = contaService.buscarPorId(id);
		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Conta>(conta, HttpStatus.OK);
	}

	@GetMapping("/BuscarTodos")
	public ResponseEntity<List<ContaDto>> buscarTodos() {
		List<Conta> contas = contaService.buscarTodos();
		List<ContaDto> contasDto = contas.stream().map(conta -> new ContaDto(conta)).collect(Collectors.toList());
		return ResponseEntity.ok(contasDto);
	}

	@DeleteMapping("/Deletar")
	public ResponseEntity<Void> deletar(@RequestParam(name = "id") Long id) {
		Conta conta = contaService.buscarPorId(id);
		if (conta == null) {
			return ResponseEntity.notFound().build();
		}
		contaService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
