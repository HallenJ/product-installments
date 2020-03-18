package com.productinstallments.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

public class PayloadModel {
	
	@Getter @Setter
	private ProductModel produto;
	
	@Getter @Setter
	private PaymentMethodModel condicaoPagamento;
	
	@JsonCreator
    public PayloadModel() {

    }

    @JsonCreator
    public PayloadModel(
    		@JsonProperty("produto") ProductModel produto, 
    		@JsonProperty("condicaoPagamento") PaymentMethodModel condicaoPagamento) {
        this.produto = produto;
        this.condicaoPagamento = condicaoPagamento;
    }
}
