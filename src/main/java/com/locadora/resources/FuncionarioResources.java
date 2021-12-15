package com.locadora.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.locadora.domain.Funcionario;
import com.locadora.services.FuncionarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class FuncionarioResources {
	@Autowired
	private FuncionarioService service;

	@ApiOperation(value = "Retorna um funcionario por id")
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Funcionario funcionario = service.findById(id);
		return ResponseEntity.ok().body(funcionario);
	}

	@ApiOperation(value = "Retorna todos os funcionarios")
	@GetMapping("/funcionarios")
	public ResponseEntity<?> findAll() {
		List<Funcionario> funcionarios = service.findAll();
		return ResponseEntity.ok().body(funcionarios);
	}

	@ApiOperation(value = "Salva um funcionario")
	@PostMapping("/funcionarios")
	public ResponseEntity<?> Save(@RequestBody Funcionario funcionario) {
		funcionario = service.save(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Retorna todos os funcionarios paginados")
	@GetMapping("/funcionarios/page")
	public ResponseEntity<Page<Funcionario>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Funcionario> funcionarios = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(funcionarios);
	}

	@ApiOperation(value = "Atualiza um funcionario")
	@PutMapping("/funcionarios")
	public ResponseEntity<?> Update(@RequestBody Funcionario funcionario) {
		funcionario = service.update(funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um funcionario por id")
	@DeleteMapping("/funcionarios/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
