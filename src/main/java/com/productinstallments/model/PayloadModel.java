package com.productinstallments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadModel {
	
	private ProductModel produto;
	
	private PaymentMethodModel condicaoPagamento;
	
	@JsonCreator
    public PayloadModel() {

    }

    @JsonCreator
    public PayloadModel(
    		@JsonProperty("produto") ProductModel produto, 
    		@JsonProperty("condicaoPagamento") PaymentMethodModel condicaoPagamento) {
        this.setProduto(produto);
        this.setCondicaoPagamento(condicaoPagamento);
    }

	public ProductModel getProduto() {
		return produto;
	}

	public void setProduto(ProductModel produto) {
		this.produto = produto;
	}

	public PaymentMethodModel getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(PaymentMethodModel condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}
}
