package com.productinstallments.service;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productinstallments.controller.InstallmentController;
import com.productinstallments.model.InterestModel;

@Service
public class InstallmentService {
	// one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private static final Logger LOGGER = LoggerFactory.getLogger(InstallmentController.class);
    private final ObjectMapper mapper = new ObjectMapper();
	/**
     * Autowired Constructor
     * @param urlRepository
     */
    @Autowired
    public InstallmentService() {
        
    }
    
    public String getInterestRate() throws ParseException, IOException {
        HttpGet request = new HttpGet("https://api.bcb.gov.br/dados/serie/bcdata.sgs.11/dados/ultimos/1?formato=json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            LOGGER.info(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            LOGGER.info(headers.toString());

            String result = EntityUtils.toString(entity);
            InterestModel[] interestList = mapper.readValue(result, InterestModel[].class);
            LOGGER.info(interestList[0].getValor());
            return interestList[0].getValor();
        }
    }
}
