package com.locadora.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.locadora.domain.Veiculo;
import com.locadora.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;

	public Veiculo findById(Integer id) {
		Optional<Veiculo> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Veiculo> findAll() {
		return repository.findAll();
	}

	public Veiculo save(Veiculo veiculo) {
		veiculo.setId(null);
		return repository.save(veiculo);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Veiculo update(Veiculo veiculo) {
		findById(veiculo.getId());
		return repository.saveAndFlush(veiculo);
	}

	public Page<Veiculo> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

}
