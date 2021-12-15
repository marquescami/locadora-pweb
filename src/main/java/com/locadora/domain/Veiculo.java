package com.locadora.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.locadora.domain.util.MarcaDoVeiculo;
import com.locadora.domain.util.ModeloDeVeiculo;
import com.locadora.domain.util.PortasDoVeiculo;
import com.locadora.domain.util.TipoDeVeiculo;

@Entity
public class Veiculo implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String placa;
	private String cor;
	private String chassi;
	private String renavam;
	
	@Enumerated(EnumType.ORDINAL)
	private TipoDeVeiculo tipoDeVeiculo;
	private PortasDoVeiculo portasDoVeiculo;
	private MarcaDoVeiculo marcaDoVeiculo;
	private ModeloDeVeiculo modeloDeVeiculo;
	

	public Veiculo(Integer id, String placa, String cor, String chassi, String renavam, TipoDeVeiculo tipoDeVeiculo, PortasDoVeiculo portasDoVeiculo, MarcaDoVeiculo marcaDoVeiculo, ModeloDeVeiculo modeloDeVeiculo) {
		super();
		this.id = id;
		this.placa = placa;
		this.cor = cor;
		this.chassi = chassi;
		this.renavam = renavam;
		this.tipoDeVeiculo = tipoDeVeiculo;
		this.portasDoVeiculo = portasDoVeiculo;
		this.marcaDoVeiculo = marcaDoVeiculo;
		this.modeloDeVeiculo = modeloDeVeiculo;
	}

	public TipoDeVeiculo getTipoDeVeiculo() {
		return tipoDeVeiculo;
	}

	public void setTipoDeVeiculo(TipoDeVeiculo tipoDeVeiculo) {
		this.tipoDeVeiculo = tipoDeVeiculo;
	}

	public Integer getId() {
		return id;
	}

	public Veiculo() {
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public PortasDoVeiculo getPortasDoVeiculo() {
		return portasDoVeiculo;
	}

	public void setPortasDoVeiculo(PortasDoVeiculo portasDoVeiculo) {
		this.portasDoVeiculo = portasDoVeiculo;
	}

	public MarcaDoVeiculo getMarcaDoVeiculo() {
		return marcaDoVeiculo;
	}

	public void setMarcaDoVeiculo(MarcaDoVeiculo marcaDoVeiculo) {
		this.marcaDoVeiculo = marcaDoVeiculo;
	}

	public ModeloDeVeiculo getModeloDeVeiculo() {
		return modeloDeVeiculo;
	}

	public void setModeloDeVeiculo(ModeloDeVeiculo modeloDeVeiculo) {
		this.modeloDeVeiculo = modeloDeVeiculo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Veiculo other = (Veiculo) obj;
		return Objects.equals(id, other.id);
	}
}
