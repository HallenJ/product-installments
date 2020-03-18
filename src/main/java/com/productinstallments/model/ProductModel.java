package com.productinstallments.model;

import java.math.BigDecimal;

public class ProductModel {
	private long codigo;
	private BigDecimal valor;
	private String nome;

    public ProductModel() {

    }

    public ProductModel(long codigo, BigDecimal valor, String nome) {
        this.setCodigo(codigo);
        this.setValor(valor);
        this.setNome(nome);
    }

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
