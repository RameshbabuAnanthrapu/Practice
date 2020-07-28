package com.example.exchangeRateViewer.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangeRateViewer.entity.RateDetails;
import com.example.exchangeRateViewer.repo.RateDetailsRepository;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;

@Service
public class ExchangeRateDataServiceImpl implements ExchangeRateDataService {

	@Autowired
	private RateDetailsRepository repo;

	@Override
	public List<RateDetails> loadExchangeRates(List<RateDetails> details) {
		
		System.out.println("Size before save all"+ details.size());

		List<RateDetails> insertedRates = repo.saveAll(details);

		repo.flush();

		return insertedRates;

	}

	@Override
	public List<RateDetails> getExchangeRatesByDate(Date date) {
		List<RateDetails> rates = repo.findExchangeRatesByDate(date);
		return rates;
	}

}
