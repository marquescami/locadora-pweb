package com.locadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.locadora.domain.Funcionario;
import com.locadora.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repository;

	public Funcionario findById(Integer id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}

	public Funcionario save(Funcionario fornecedor) {
		fornecedor.setId(null);
		return repository.save(fornecedor);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Funcionario update(Funcionario fornecedor) {
		findById(fornecedor.getId());
		return repository.saveAndFlush(fornecedor);
	}

	public Page<Funcionario> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}
}
