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
import com.example.exchangeRateViewer.repo.RateDetailsRepository;

@RunWith(SpringRunner.class)
public class ExchangeRateDataServiceImplTest {
	
	@InjectMocks
	private  ExchangeRateDataServiceImpl dataServiceImpl;
	
	@Mock
	private RateDetailsRepository repo;
	
	@Before
	public void setup(){
		List<RateDetails> rateList = buildInput();
		Mockito.when(repo.findExchangeRatesByDate(Date.valueOf("2020-07-01"))).thenReturn(rateList);
		Mockito.when(repo.saveAll(ArgumentMatchers.anyList())).thenReturn(rateList);
		
		
		
	}
	
	@Test
	public void testGetExchangeRatesByDate(){
		
		List<RateDetails> resList = dataServiceImpl.getExchangeRatesByDate(Date.valueOf("2020-07-01"));
		Assert.assertEquals("HKD", resList.get(0).getCurrency());
		
	}
	
	@Test
	public void testLoadExchangeRates() {
		
		
		List<RateDetails> res = dataServiceImpl.loadExchangeRates(buildInput());
		Assert.assertEquals("HKD", res.get(0).getCurrency());
		
		
	}
	
	private List<RateDetails> buildInput(){
		
		List<RateDetails> rateList =  new ArrayList<>();
		RateDetails rate = new RateDetails();
		rate.setBase("EUR");
		rate.setCurrency("HKD");
		rate.setDate(Date.valueOf("2020-07-01"));
		rate.setRate(new BigDecimal(0.90));
		rateList.add(rate);
		
		return rateList;
		
		
	}
}
