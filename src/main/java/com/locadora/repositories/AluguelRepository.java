package com.locadora.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.locadora.domain.Aluguel;
import com.locadora.domain.Cliente;
import com.locadora.domain.Veiculo;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

	@Query("select a from Aluguel a where a.dataInicio>=?1 and a.dataFim<=?2 and a.cliente=?3 order by a.dataInicio")
	public List<Aluguel> findByDataCliente(LocalDate dataInicio, LocalDate dataFim, Cliente cliente);

	@Query("select a from Aluguel a where a.dataInicio>=?1 and a.dataFim<=?2 and a.veiculo=?3 order by a.dataInicio")
	public List<Aluguel> findByDataVeiculo(LocalDate dataInicio, LocalDate dataFim, Veiculo veiculo);

	public List<Aluguel> findByVeiculo(Veiculo veiculo);

	public List<Aluguel> findByCliente(Cliente cliente);
}