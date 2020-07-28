package com.example.exchangeRateViewer.service;

import com.example.exchangeRateViewer.model.ExchangeRateModel;

public interface ExchangeRateViewService {
	
	ExchangeRateModel getExchangeRatesByDate(String date);

}
