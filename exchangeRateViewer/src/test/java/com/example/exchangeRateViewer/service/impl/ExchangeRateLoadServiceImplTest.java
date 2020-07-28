package com.example.exchangeRateViewer.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchangeRateViewer.entity.RateDetails;
import com.example.exchangeRateViewer.exception.ApplicationException;
import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExternalApiResponse;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;
import com.example.exchangeRateViewer.service.RateApiProxyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class ExchangeRateLoadServiceImplTest {

	@InjectMocks
	private ExchangeRateLoadServiceImpl loadService;

	@Mock
	private RateApiProxyService proxy;

	@Mock
	private ExchangeRateDataService dataService;

	@Before
	public void setup() throws JsonMappingException, JsonProcessingException {
		ExternalApiResponse apiRseponse = new ExternalApiResponse();
		apiRseponse.setBase("EUR");
		apiRseponse.setDate("2020-07-01");
		String rates = "{\"USD\":0.8 ,\"GBP\":0.9, \"HKD\":0.7}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(rates);
		apiRseponse.setRates(node);
		Mockito.when(proxy.getExchangeRatesByDate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())).thenReturn(apiRseponse);

		Mockito.when(dataService.loadExchangeRates(ArgumentMatchers.anyList())).thenReturn(buildRates());

	}

	@Test
	public void testUploadExchangeRates() {

		ExchangeRateModel exRateModel = loadService.uploadExchangeRates("test1", "test2");

		Assert.assertEquals("EUR", exRateModel.getBase());
	}

	@Test(expected = ApplicationException.class)
	public void testUploadExchangeRates_NoDataFromExternalApi() {

		Mockito.when(proxy.getExchangeRatesByDate(ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
				ArgumentMatchers.anyString())).thenReturn(null);
		loadService.uploadExchangeRates("test1", "test2");

	}

	private List<RateDetails> buildRates() {

		List<RateDetails> rateList = new ArrayList<>();
		RateDetails rate1 = new RateDetails();
		rate1.setBase("EUR");
		rate1.setCurrency("HKD");
		rate1.setDate(Date.valueOf("2020-07-01"));
		rate1.setRate(new BigDecimal(0.60));
		rateList.add(rate1);

		RateDetails rate2 = new RateDetails();
		rate2.setBase("EUR");
		rate2.setCurrency("GBP");
		rate2.setDate(Date.valueOf("2020-07-01"));
		rate2.setRate(new BigDecimal(0.90));
		rateList.add(rate2);

		RateDetails rate3 = new RateDetails();
		rate3.setBase("EUR");
		rate3.setCurrency("USD");
		rate3.setDate(Date.valueOf("2020-07-01"));
		rate3.setRate(new BigDecimal(0.80));
		rateList.add(rate3);

		return rateList;

	}

}
