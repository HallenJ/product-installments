package com.productinstallments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstallmentService {
	/**
     * Autowired Constructor
     * @param urlRepository
     */
    @Autowired
    public InstallmentService() {
        
    }
    
    public String getInterestRate() {
    	return "0.0115";
    }
}
