package com.productinstallments.model;

import java.math.BigDecimal;

public class ResponseItemModel {
	private int numeroParcela;
	private BigDecimal valor;
	private BigDecimal taxaJurosAoMes;
	
	public ResponseItemModel() {

    }

    public ResponseItemModel(int numeroParcela, BigDecimal valor, BigDecimal taxaJurosAoMes) {
    	this.setNumeroParcela(numeroParcela);
    	this.setValor(valor);
    	this.setTaxaJurosAoMes(taxaJurosAoMes);
    }

	public int getNumeroParcela() {
		return numeroParcela;
	}

	public void setNumeroParcela(int numeroParcela) {
		this.numeroParcela = numeroParcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTaxaJurosAoMes() {
		return taxaJurosAoMes;
	}

	public void setTaxaJurosAoMes(BigDecimal taxaJurosAoMes) {
		this.taxaJurosAoMes = taxaJurosAoMes;
	}

}
