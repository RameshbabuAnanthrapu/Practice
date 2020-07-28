package com.example.exchangeRateViewer.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.exchangeRateViewer.entity.RateDetails;
import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;



@RunWith(SpringRunner.class)
public class ExchangeRateViewServiceImplTest {
	
	@InjectMocks
	private ExchangeRateViewServiceImpl viewService;
	
	@Mock
	private ExchangeRateDataService dataService;
	
	@Before
	public void setup() {
		Mockito.when(dataService.getExchangeRatesByDate(Date.valueOf("2020-07-01"))).thenReturn(buildRates());
	}
	
	@Test
	public void testGetExchangeRatesByDate(){
		
		ExchangeRateModel model = viewService.getExchangeRatesByDate("2020-07-01");
		Assert.assertEquals("EUR", model.getBase());
		
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
