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

import com.locadora.domain.Veiculo;
import com.locadora.services.VeiculoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class VeiculoResource {

	@Autowired
	private VeiculoService service;

	@ApiOperation(value = "Retorna um veiculo por id")
	@GetMapping("/veiculos/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Veiculo veiculo = service.findById(id);
		return ResponseEntity.ok().body(veiculo);
	}

	@ApiOperation(value = "Retorna todos os veiculos")
	@GetMapping("/veiculos")
	public ResponseEntity<?> findAll() {
		List<Veiculo> veiculos = service.findAll();
		return ResponseEntity.ok().body(veiculos);
	}

	@ApiOperation(value = "Salva um veiculo")
	@PostMapping("/veiculos")
	public ResponseEntity<?> Save(@RequestBody Veiculo veiculo) {
		veiculo = service.save(veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Retorna todos os veiculos paginados")
	@GetMapping("/veiculos/page")
	public ResponseEntity<Page<Veiculo>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Veiculo> veiculos = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(veiculos);
	}

	@ApiOperation(value = "Atualiza um veiculo")
	@PutMapping("/veiculos")
	public ResponseEntity<?> Update(@RequestBody Veiculo veiculo) {
		veiculo = service.update(veiculo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um veiculo por id")
	@DeleteMapping("/veiculos/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
