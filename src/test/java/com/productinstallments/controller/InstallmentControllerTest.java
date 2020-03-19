package com.productinstallments.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productinstallments.model.PayloadModel;
import com.productinstallments.model.PaymentMethodModel;
import com.productinstallments.model.ProductModel;
import com.productinstallments.service.InstallmentService;

@RunWith(SpringRunner.class)
@WebMvcTest(InstallmentController.class)
class InstallmentControllerTest {
	
	@Autowired
    private MockMvc mvc;
 
    @MockBean
    private InstallmentService installmentService;
    
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
    
	@Test
	void getLessThan7InstallmentsInstallmentListTest() throws Exception {
		given(this.installmentService.getInterestRate()).willReturn("0.0115");
		ProductModel produto = new ProductModel(123, new BigDecimal("10000.00") ,"Nome do Produto");
		PaymentMethodModel condicaoPagamento = new PaymentMethodModel(new BigDecimal("5000.00"), 6);
		PayloadModel payload = new PayloadModel(produto, condicaoPagamento);
		mvc.perform(
			post("/installments")
			.content(asJsonString(payload))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
		
		reset(installmentService);
	}
	
	@Test
	void getMoreThan7InstallmentsInstallmentListTest() throws Exception {
		given(this.installmentService.getInterestRate()).willReturn("0.0115");
		ProductModel produto = new ProductModel(123, new BigDecimal("10000.00") ,"Nome do Produto");
		PaymentMethodModel condicaoPagamento = new PaymentMethodModel(new BigDecimal("5000.00"), 10);
		PayloadModel payload = new PayloadModel(produto, condicaoPagamento);
		mvc.perform(
			post("/installments")
			.content(asJsonString(payload))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is2xxSuccessful())
			.andExpect(MockMvcResultMatchers.jsonPath("$.[*]").isNotEmpty());
		
		reset(installmentService);
	}
	
	@Test
	void getErrorInstallmentListTest() throws Exception {
		given(this.installmentService.getInterestRate()).willReturn("0.0115");
		ProductModel produto = new ProductModel(123, new BigDecimal("2000.00") ,"Nome do Produto");
		PaymentMethodModel condicaoPagamento = new PaymentMethodModel(new BigDecimal("5000.00"), 10);
		PayloadModel payload = new PayloadModel(produto, condicaoPagamento);
		mvc.perform(
			post("/installments")
			.content(asJsonString(payload))
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().is4xxClientError());
		
		reset(installmentService);
	}

}
