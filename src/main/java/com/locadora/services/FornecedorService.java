package com.locadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.locadora.domain.Fornecedor;
import com.locadora.repositories.FornecedorRepository;

@Service
public class FornecedorService {
	@Autowired
	private FornecedorRepository repository;

	public Fornecedor findById(Integer id) {
		Optional<Fornecedor> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Fornecedor> findAll() {
		return repository.findAll();
	}

	public Fornecedor save(Fornecedor fornecedor) {
		fornecedor.setId(null);
		return repository.save(fornecedor);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Fornecedor update(Fornecedor fornecedor) {
		findById(fornecedor.getId());
		return repository.saveAndFlush(fornecedor);
	}

	public Page<Fornecedor> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}
}
