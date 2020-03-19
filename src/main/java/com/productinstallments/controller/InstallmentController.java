package com.productinstallments.controller;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.productinstallments.model.PayloadModel;
import com.productinstallments.model.PaymentMethodModel;
import com.productinstallments.model.ProductModel;
import com.productinstallments.model.ResponseItemModel;
import com.productinstallments.service.InstallmentService;

@RestController
public class InstallmentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InstallmentController.class);
    private final InstallmentService installmentService;
    
    public InstallmentController(InstallmentService installmentService) {
		this.installmentService = installmentService;
    }

    @RequestMapping(value = "/installments", method=RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<String> getInstallmentList(@RequestBody @Valid final PayloadModel payloadModel, HttpServletRequest request) throws Exception {
    	LOGGER.info("Processando Informações de Pagamento");
    	ProductModel produto = payloadModel.getProduto();
        PaymentMethodModel condicaoPagamento = payloadModel.getCondicaoPagamento();
        int parcelas = condicaoPagamento.getQtdeParcelas();
        BigDecimal valorProduto = produto.getValor();
        BigDecimal entrada = condicaoPagamento.getValorEntrada();

    	ArrayList<ResponseItemModel> installmentList = this.createInstallmentList(parcelas, entrada, valorProduto);
    	if (installmentList == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    	}
        return new ResponseEntity<String>(new Gson().toJson(installmentList), HttpStatus.CREATED);
    }
    
    private ArrayList<ResponseItemModel> createInstallmentList(int parcelas, BigDecimal entrada, BigDecimal valorProduto) throws Exception {
    	BigDecimal remainingFullPrice;
    	BigDecimal installmentPrice;
    	BigDecimal parcelasDecimal = new BigDecimal(Integer.toString(parcelas));
    	ArrayList<ResponseItemModel> installments = new ArrayList<ResponseItemModel>();
    	BigDecimal interestRate = new BigDecimal(this.installmentService.getInterestRate());
    	
        if (parcelas < 7) {
        	remainingFullPrice = valorProduto.subtract(entrada);
        }
        else {
        	BigDecimal compoundInterest = interestRate.add(BigDecimal.ONE).pow(parcelas);
        	BigDecimal newPrice = valorProduto.multiply(compoundInterest);
        	remainingFullPrice = newPrice.subtract(entrada);
        	LOGGER.info("Acima de seis parcelas, novo valor computado é: R$" + newPrice.toString());
        	LOGGER.info("O valor restante a ser parcelado é: R$" + remainingFullPrice.toString());
        	installmentPrice = newPrice.divide(parcelasDecimal, 2, RoundingMode.CEILING);
        }
        
        if (remainingFullPrice.compareTo(BigDecimal.ZERO) < 0) {
        	LOGGER.error("A entrada é maior que o preço do produto!");
        	return null;
        }

    	installmentPrice = remainingFullPrice.divide(parcelasDecimal, 2, RoundingMode.CEILING);
    	
        for(int i = 0; i < parcelas + 1; i++) {
        	ResponseItemModel responseItem = new ResponseItemModel();
    		responseItem.setNumeroParcela(i);
        	if (i == 0) {
        		responseItem.setValor(entrada);
        	}
        	else {
        		responseItem.setValor(installmentPrice);
        	}
        	responseItem.setTaxaJurosAoMes(interestRate);
        	installments.add(responseItem);
        }
        
        return installments;
    }
    
}