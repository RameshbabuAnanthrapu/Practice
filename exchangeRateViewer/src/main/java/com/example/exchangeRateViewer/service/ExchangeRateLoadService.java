package com.example.exchangeRateViewer.service;


import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExternalApiResponse;

public interface ExchangeRateLoadService {
	
	ExchangeRateModel uploadExchangeRates(String userAgent, String symbols);

}
