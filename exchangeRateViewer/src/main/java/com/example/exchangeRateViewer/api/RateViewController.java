package com.example.exchangeRateViewer.api;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.exchangeRateViewer.exception.ApplicationException;
import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExchangeRateViewResponse;

import com.example.exchangeRateViewer.service.ExchangeRateLoadService;
import com.example.exchangeRateViewer.service.ExchangeRateViewService;

@RestController
@RequestMapping("/api/")
public class RateViewController {

	private static final Logger LOG = LoggerFactory.getLogger(RateViewController.class);

	@Autowired
	private ExchangeRateLoadService loadService;

	@Autowired
	private ExchangeRateViewService viewService;

	@Value("${header.user.agent}")
	private String userAgent;

	@Value("${rate.api.symbols}")
	private String symbols;

	@GetMapping("/rates")
	public ResponseEntity<ExchangeRateViewResponse> loadExchangeRates() {
		LOG.info("RateViewController::loadExchangeRates");

		return buildResponse(loadService.uploadExchangeRates(userAgent, symbols));

	}

	@GetMapping("/rates/{date}")
	public ResponseEntity<ExchangeRateViewResponse> getExchangeRates(@PathVariable("date") String date) {
		LOG.info("RateViewController::getExchangeRates");
		checkDateString(date);

		return buildResponse(viewService.getExchangeRatesByDate(date));

	}

	private ResponseEntity<ExchangeRateViewResponse> buildResponse(ExchangeRateModel model) {

		ExchangeRateViewResponse response = new ExchangeRateViewResponse();
		response.setExchangeRateInfo(model);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void checkDateString(String date) {
		LOG.info("RateViewController::checkDateString");
		try {
			Date.valueOf(date);
		} catch (IllegalArgumentException e) {
			LOG.error("failed to parse input parameter {} to type Date", date);
			throw new ApplicationException("Invalid input parameter  :" + date, HttpStatus.BAD_REQUEST);
		}
	}

}
