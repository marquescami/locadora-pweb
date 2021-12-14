package com.locadora.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locadora.domain.util.TipoDeSeguro;

@Entity
public class Aluguel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "dataInicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "dataFim", nullable = false, columnDefinition = "DATE")
	private LocalDate dataFim;

	@ManyToOne
	@JoinColumn(name = "veiculo_id_fk")
	private Veiculo veiculo;

	@ManyToOne
	@JoinColumn(name = "cliente_id_fk")
	private Cliente cliente;

	@Enumerated(EnumType.ORDINAL)
	private TipoDeSeguro tipoDeSeguro;

	public Aluguel() {
		super();
	}

	public Aluguel(Integer id, LocalDate dataInicio, LocalDate dataFim, Veiculo veiculo, Cliente cliente,
			TipoDeSeguro tipoDeSeguro) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.tipoDeSeguro = tipoDeSeguro;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoDeSeguro getTipoDeSeguro() {
		return tipoDeSeguro;
	}

	public void setTipoDeSeguro(TipoDeSeguro tipoDeSeguro) {
		this.tipoDeSeguro = tipoDeSeguro;
	}

}
