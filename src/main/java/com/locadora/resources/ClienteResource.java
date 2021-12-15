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

import com.locadora.domain.Cliente;
import com.locadora.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("/")
@Api(value = "API REST cliente")
@CrossOrigin(origins = "*")
public class ClienteResource {
	@Autowired
	private ClienteService service;

	@ApiOperation(value = "Retorna um cliente por id")
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}

	@ApiOperation(value = "Retorna todos os clientes")
	@GetMapping("/clientes")
	public ResponseEntity<?> findAll() {
		List<Cliente> clientes = service.findAll();
		return ResponseEntity.ok().body(clientes);
	}

	@ApiOperation(value = "Salva um cliente")
	@PostMapping("/clientes")
	public ResponseEntity<?> Save(@RequestBody Cliente cliente) {
		cliente = service.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Retorna todos os clientes paginados")
	@GetMapping("/clientes/page")
	public ResponseEntity<Page<Cliente>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Cliente> clientes = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(clientes);
	}

	@ApiOperation(value = "Atualiza um cliente")
	@PutMapping("/clientes")
	public ResponseEntity<?> Update(@RequestBody Cliente cliente) {
		cliente = service.update(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um cliente por id")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
