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

import com.locadora.domain.Usuario;
import com.locadora.services.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
public class UsuarioResource {
	@Autowired
	private UsuarioService service;

	@ApiOperation(value = "Retorna um usuario por id")
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@ApiOperation(value = "Retorna todos os usuarios")
	@GetMapping("/usuarios")
	public ResponseEntity<?> findAll() {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@ApiOperation(value = "Salva um usuario")
	@PostMapping("/usuarios")
	public ResponseEntity<?> Save(@RequestBody Usuario usuario) {
		usuario = service.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Retorna todos os usuarios paginados")
	@GetMapping("/usuarios/page")
	public ResponseEntity<Page<Usuario>> findAllPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "2") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Usuario> usuarios = service.findAllPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(usuarios);
	}

	@ApiOperation(value = "Atualiza um usuario")
	@PutMapping("/usuarios")
	public ResponseEntity<?> Update(@RequestBody Usuario usuario) {
		usuario = service.update(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um usuario por id")
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
