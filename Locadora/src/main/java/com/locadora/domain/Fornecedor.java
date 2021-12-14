package com.locadora.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.locadora.domain.util.TipoDeProduto;

@Entity
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String cnpj;

	@Column(nullable = false)
	private String nome;

	@Enumerated(EnumType.ORDINAL)
	private TipoDeProduto tipoDeProduto;

	public Fornecedor() {
		super();
	}

	public Fornecedor(Integer id, String cnpj, String nome, TipoDeProduto tipoDeProduto) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
		this.tipoDeProduto = tipoDeProduto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

}
