
package com.locadora.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.locadora.domain.Aluguel;
import com.locadora.domain.Cliente;
import com.locadora.domain.Veiculo;
import com.locadora.repositories.AluguelRepository;

@Service
public class AluguelService {
	@Autowired
	private AluguelRepository repository;

	public Aluguel findById(Integer id) {
		Optional<Aluguel> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Aluguel> findAll() {
		return repository.findAll();
	}

	public Aluguel save(Aluguel aluguel) {
		aluguel.setId(null);
		return repository.save(aluguel);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Aluguel update(Aluguel aluguel) {
		findById(aluguel.getId());
		return repository.saveAndFlush(aluguel);
	}

	public Page<Aluguel> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

	public List<Aluguel> findByDataCliente(LocalDate dataInicio, LocalDate dataFim, Cliente cliente) {
		return repository.findByDataCliente(dataInicio, dataFim, cliente);
	}

	public List<Aluguel> findByDataVeiculo(LocalDate dataInicio, LocalDate dataFim, Veiculo veiculo) {
		return repository.findByDataVeiculo(dataInicio, dataFim, veiculo);
	}

	public List<Aluguel> findByVeiculo(Veiculo veiculo) {
		return repository.findByVeiculo(veiculo);
	}

	public List<Aluguel> findByCliente(Cliente cliente) {
		return repository.findByCliente(cliente);
	}
}
