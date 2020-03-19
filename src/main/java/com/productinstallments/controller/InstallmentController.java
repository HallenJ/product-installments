package com.productinstallments.controller;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String getInstallmentList(@RequestBody @Valid final PayloadModel payloadModel, HttpServletRequest request) throws Exception {
    	LOGGER.info("Processando Informações de Pagamento");
    	ProductModel produto = payloadModel.getProduto();
        PaymentMethodModel condicaoPagamento = payloadModel.getCondicaoPagamento();
        int parcelas = condicaoPagamento.getQtdeParcelas();
        BigDecimal valorProduto = produto.getValor();
        ArrayList<ResponseItemModel> installmentList = this.createInstallmentList(parcelas, valorProduto);
        return new Gson().toJson(installmentList);
    }
    
    private ArrayList<ResponseItemModel> createInstallmentList(int parcelas, BigDecimal valorProduto) throws Exception {
    	BigDecimal installmentPrice;
    	BigDecimal parcelasDecimal = new BigDecimal(Integer.toString(parcelas));
    	ArrayList<ResponseItemModel> installments = new ArrayList<ResponseItemModel>();
    	BigDecimal interestRate = new BigDecimal(this.installmentService.getInterestRate());
    	
        if (parcelas < 7) {
        	installmentPrice = valorProduto.divide(parcelasDecimal, 2, RoundingMode.CEILING);
        }
        else {
        	BigDecimal compoundInterest = interestRate.add(BigDecimal.ONE).pow(parcelas);
        	BigDecimal newPrice = valorProduto.multiply(compoundInterest);
        	LOGGER.info("Acima de seis parcelas, novo valor computado é: " + newPrice.toString());
        	installmentPrice = newPrice.divide(parcelasDecimal, 2, RoundingMode.CEILING);
        }
        
        for(int i = 0; i < parcelas; i++) {
        	ResponseItemModel responseItem = new ResponseItemModel(i + 1, installmentPrice, interestRate);
        	installments.add(responseItem);
        }
        
        return installments;
    }
    
}