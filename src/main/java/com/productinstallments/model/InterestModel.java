package com.productinstallments.model;

public class InterestModel {
	private String data;
	private String valor;

    public InterestModel() {

    }

    public InterestModel(String data, String valor) {
        this.setData(data);
        this.setValor(valor);
    }

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
