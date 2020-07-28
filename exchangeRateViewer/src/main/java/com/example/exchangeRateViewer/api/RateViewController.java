package com.example.exchangeRateViewer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExternalApiResponse;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;
import com.example.exchangeRateViewer.service.ExchangeRateLoadService;
import com.example.exchangeRateViewer.service.RateApiProxyService;

@RestController
@RequestMapping("/rates")
public class RateViewController {

	@Autowired
	private RateApiProxyService proxy;
	
	@Autowired
	private ExchangeRateLoadService loadService;

	@Value("${header.user.agent}")
	private String userAgent;

	@Value("${rate.api.symbols}")
	private String symbols;

	@GetMapping("/")
	public ExchangeRateModel loadExchangeRates() {

		System.out.println("calling proxy service to fetch rates info");

		return loadService.uploadExchangeRates(userAgent, symbols);

	}

}
