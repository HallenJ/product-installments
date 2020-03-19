package com.productinstallments.model;

import java.math.BigDecimal;

public class PaymentMethodModel {
	private BigDecimal valorEntrada;
	private int qtdeParcelas;
	
	public PaymentMethodModel() {

    }

    public PaymentMethodModel(BigDecimal valorEntrada, int qtdeParcelas) {
        this.setValorEntrada(valorEntrada);
        this.setQtdeParcelas(qtdeParcelas);
    }

	public BigDecimal getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(BigDecimal valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
}
