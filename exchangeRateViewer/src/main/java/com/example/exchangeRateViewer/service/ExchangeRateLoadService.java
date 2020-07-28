package com.example.exchangeRateViewer.service;


import com.example.exchangeRateViewer.model.ExchangeRateModel;


public interface ExchangeRateLoadService {
	
	ExchangeRateModel uploadExchangeRates(String userAgent, String symbols);

}
