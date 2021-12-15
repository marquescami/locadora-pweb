package com.locadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.locadora.domain.Cliente;
import com.locadora.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente save(Cliente cliente) {
		cliente.setId(null);
		return repository.save(cliente);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Cliente update(Cliente cliente) {
		findById(cliente.getId());
		return repository.saveAndFlush(cliente);
	}

	public Page<Cliente> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}
}
