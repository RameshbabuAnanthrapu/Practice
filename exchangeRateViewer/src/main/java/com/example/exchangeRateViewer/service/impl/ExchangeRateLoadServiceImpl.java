package com.example.exchangeRateViewer.service.impl;

import java.time.LocalDate;

import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangeRateViewer.entity.RateDetails;
import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExternalApiResponse;
import com.example.exchangeRateViewer.model.RateModel;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;
import com.example.exchangeRateViewer.service.ExchangeRateLoadService;
import com.example.exchangeRateViewer.service.RateApiProxyService;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class ExchangeRateLoadServiceImpl implements ExchangeRateLoadService {

	@Autowired
	private RateApiProxyService proxy;

	@Autowired
	private ExchangeRateDataService dataService;

	@Override
	public ExchangeRateModel uploadExchangeRates(String userAgent, String symbols) {

		LocalDate currentDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate pastOneYear = currentDate.minusYears(1).with(TemporalAdjusters.firstDayOfMonth());
		ExternalApiResponse response = null;
		ExchangeRateModel model = new ExchangeRateModel();
		List<RateModel> ratesModelList = new ArrayList<>();

		for (LocalDate date = pastOneYear; date
				.isBefore(currentDate); date = date.with(TemporalAdjusters.firstDayOfNextMonth())) {

			response = proxy.getExchangeRatesByDate(userAgent, symbols, date.toString());
			model.setBase(response.getBase());

			List<RateDetails> ratesList = new ArrayList<>();
			Iterator<Entry<String, JsonNode>> jsonEntries = response.getRates().fields();

			while (jsonEntries.hasNext()) {

				RateDetails rates = new RateDetails();
				RateModel rateModel = new RateModel();
				Entry<String, JsonNode> e = jsonEntries.next();
				rates.setCurrency(e.getKey());
				rates.setRate(e.getValue().decimalValue());
				rates.setBase(response.getBase());
				rates.setDate(response.getDate());
				ratesList.add(rates);

				rateModel.setCurrencyCode(e.getKey());
				rateModel.setExChangeRate(e.getValue().decimalValue());
				rateModel.setDate(response.getDate());
				ratesModelList.add(rateModel);

			}
			model.setRates(ratesModelList);

			dataService.loadExchangeRates(ratesList);

		}

		return model;
	}

	

}
