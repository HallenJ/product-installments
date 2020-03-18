package com.productinstallments.model;

import lombok.Getter;
import lombok.Setter;

public class ProductModel {
	@Getter @Setter
	private long codigo;

	@Getter @Setter
	private float valor;
	
	@Getter @Setter
	private String nome;

    public ProductModel() {

    }

    public ProductModel(long codigo, float valor, String nome) {
        this.codigo = codigo;
        this.valor = valor;
        this.nome = nome;
    }
}
