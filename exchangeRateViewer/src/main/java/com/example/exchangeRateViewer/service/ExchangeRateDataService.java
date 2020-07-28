package com.example.exchangeRateViewer.service;

import java.util.List;

import com.example.exchangeRateViewer.entity.RateDetails;

public interface ExchangeRateDataService {

	List<RateDetails> loadExchangeRates(List<RateDetails> details);

}
