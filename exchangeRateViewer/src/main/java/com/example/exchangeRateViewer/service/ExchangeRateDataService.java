package com.example.exchangeRateViewer.service;


import java.sql.Date;
import java.util.List;

import com.example.exchangeRateViewer.entity.RateDetails;

public interface ExchangeRateDataService {

	List<RateDetails> loadExchangeRates(List<RateDetails> details);
	List<RateDetails> getExchangeRatesByDate(Date date);
	void removeOldrecords();

}
