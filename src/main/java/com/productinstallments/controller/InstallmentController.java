package com.productinstallments.controller;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    	throw new Exception("TODO");
    }
    
    private ArrayList<ResponseItemModel> createInstallmentList(int parcelas, BigDecimal valorProduto) throws Exception {
    	BigDecimal installmentPrice;
    	BigDecimal parcelasDecimal = new BigDecimal(Integer.toString(parcelas));
    	ArrayList<ResponseItemModel> installments = new ArrayList<ResponseItemModel>();
    	
        if (parcelas < 7) {
        	installmentPrice = valorProduto.divide(parcelasDecimal, 2, RoundingMode.DOWN);
        }
        else {
        	throw new Exception("TODO");
        }
        
        for(int i = 0; i < parcelas; i++) {
        	BigDecimal interestRate = new BigDecimal("1.15");
        	ResponseItemModel responseItem = new ResponseItemModel(i, installmentPrice, interestRate);
        	installments.add(responseItem);
        }
        
        return installments;
    }
    
}