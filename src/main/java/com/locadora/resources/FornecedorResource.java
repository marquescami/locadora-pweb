package com.locadora.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.locadora.domain.Fornecedor;
import com.locadora.services.FornecedorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
@Api(value = "API REST fornecedor")
@CrossOrigin(origins = "*")
public class FornecedorResource {
	@Autowired
	private FornecedorService service;

	@ApiOperation(value = "Retorna um fornecedor por id")
	@GetMapping("/fornecedores/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Fornecedor fornecedor = service.findById(id);
		return ResponseEntity.ok().body(fornecedor);
	}

	@ApiOperation(value = "Retorna todos os fornecedores")
	@GetMapping("/fornecedores")
	public ResponseEntity<?> findAll() {
		List<Fornecedor> fornecedores = service.findAll();
		return ResponseEntity.ok().body(fornecedores);
	}

	@ApiOperation(value = "Salva um fornecedor")
	@PostMapping("/fornecedores")
	public ResponseEntity<?> Save(@RequestBody Fornecedor fornecedor) {
		fornecedor = service.save(fornecedor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Retorna todos os fornecedores paginados")
	@GetMapping("/fornecedores/page")
	public ResponseEntity<Page<Fornecedor>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Fornecedor> fornecedores = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(fornecedores);
	}

	@ApiOperation(value = "Atualiza um fornecedor")
	@PutMapping("/fornecedores")
	public ResponseEntity<?> Update(@RequestBody Fornecedor fornecedor) {
		fornecedor = service.update(fornecedor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(fornecedor.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um fornecedor por id")
	@DeleteMapping("/fornecedores/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
