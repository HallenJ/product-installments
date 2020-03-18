package com.productinstallments.controller;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.productinstallments.model.PayloadModel;
import com.productinstallments.service.InstallmentService;

@RestController
public class InstallmentController {
    //private static final Logger LOGGER = LoggerFactory.getLogger(InstallmentController.class);
    private final InstallmentService installmentService;
    
    public InstallmentController(InstallmentService installmentService) {
		this.installmentService = installmentService;
    }

    @RequestMapping(value = "/installments", method=RequestMethod.POST, consumes = {"application/json"})
    public String shortenUrl(@RequestBody @Valid final PayloadModel payloadModel, HttpServletRequest request) throws Exception {
        throw new Exception("TODO");
    }
}

class ShortenRequest{
    private String url;

    @JsonCreator
    public ShortenRequest() {

    }

    @JsonCreator
    public ShortenRequest(@JsonProperty("url") String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}