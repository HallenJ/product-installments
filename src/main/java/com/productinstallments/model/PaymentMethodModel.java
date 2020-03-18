package com.productinstallments.model;

public class PaymentMethodModel {
	private float valorEntrada;
	private int qtdeParcelas;
	
	public PaymentMethodModel() {

    }

    public PaymentMethodModel(float valorEntrada, int qtdeParcelas) {
        this.setValorEntrada(valorEntrada);
        this.setQtdeParcelas(qtdeParcelas);
    }

	public float getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(float valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public int getQtdeParcelas() {
		return qtdeParcelas;
	}

	public void setQtdeParcelas(int qtdeParcelas) {
		this.qtdeParcelas = qtdeParcelas;
	}
}
