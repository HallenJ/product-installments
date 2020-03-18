package com.productinstallments.model;

import lombok.Getter;
import lombok.Setter;

public class PaymentMethodModel {
	@Getter @Setter
	private float valorEntrada;
	
	@Getter @Setter
	private int qtdeParcelas;
	
	public PaymentMethodModel() {

    }

    public PaymentMethodModel(float valorEntrada, int qtdeParcelas) {
        this.valorEntrada = valorEntrada;
        this.qtdeParcelas = qtdeParcelas;
    }
}
