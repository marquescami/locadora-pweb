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

import com.locadora.domain.Aluguel;
import com.locadora.domain.Cliente;
import com.locadora.domain.Veiculo;
import com.locadora.services.AluguelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@Api(value = "API REST aluguel")
@CrossOrigin(origins = "*")

public class AluguelResource {

	@Autowired

	private AluguelService service;

	@ApiOperation(value = "Retorna um aluguel por id")

	@GetMapping("/alugueis/{id}")

	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Aluguel aluguel = service.findById(id);

		return ResponseEntity.ok().body(aluguel);

	}

	@ApiOperation(value = "Retorna todos os alugueis")

	@GetMapping("/alugueis")

	public ResponseEntity<?> findAll() {

		List<Aluguel> alugueis = service.findAll();

		return ResponseEntity.ok().body(alugueis);

	}

	@ApiOperation(value = "Salva um aluguel")

	@PostMapping("/alugueis")

	public ResponseEntity<?> Save(@RequestBody Aluguel aluguel) {

		aluguel = service.save(aluguel);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluguel.getId())

				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@ApiOperation(value = "Retorna todos os alugueis paginados")

	@GetMapping("/alugueis/page")

	public ResponseEntity<Page<Aluguel>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,

			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,

			@RequestParam(value = "direction", defaultValue = "ASC") String direction,

			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {

		Page<Aluguel> alugueis = service.findAllPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(alugueis);

	}

	@ApiOperation(value = "Atualiza um aluguel")

	@PutMapping("/alugueis")

	public ResponseEntity<?> Update(@RequestBody Aluguel aluguel) {

		aluguel = service.update(aluguel);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluguel.getId())

				.toUri();

		return ResponseEntity.created(uri).build();

	}

	@ApiOperation(value = "Deleta um aluguel por id")

	@DeleteMapping("/alugueis/{id}")

	public ResponseEntity<?> deleteById(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.noContent().build();

	}

	@ApiOperation(value = "Retorna os alugueis por data e cliente")

	@GetMapping("/alugueis/dataCliente")

	public ResponseEntity<?> findByDataCliente(@RequestBody Aluguel aluguel) {

		List<Aluguel> alugueis = service.findByDataCliente(aluguel.getDataInicio(), aluguel.getDataFim(),

				aluguel.getCliente());

		return ResponseEntity.ok().body(alugueis);

	}

	@ApiOperation(value = "Retorna os alugueis por data e veiculo")

	@GetMapping("/alugueis/dataVeiculo")

	public ResponseEntity<?> findByDataVeiculo(@RequestBody Aluguel aluguel) {

		List<Aluguel> alugueis = service.findByDataVeiculo(aluguel.getDataInicio(), aluguel.getDataFim(),

				aluguel.getVeiculo());

		return ResponseEntity.ok().body(alugueis);

	}

	@ApiOperation(value = "Retorna os alugueis por veiculo")

	@GetMapping("/alugueis/veiculo")

	public ResponseEntity<?> findByVeiculo(@RequestBody Veiculo veiculo) {

		List<Aluguel> alugueis = service.findByVeiculo(veiculo);

		return ResponseEntity.ok().body(alugueis);

	}

	@ApiOperation(value = "Retorna os alugueis por cliente")

	@GetMapping("/alugueis/cliente")

	public ResponseEntity<?> findByCleinte(@RequestBody Cliente cliente) {

		List<Aluguel> alugueis = service.findByCliente(cliente);

		return ResponseEntity.ok().body(alugueis);

	}

}