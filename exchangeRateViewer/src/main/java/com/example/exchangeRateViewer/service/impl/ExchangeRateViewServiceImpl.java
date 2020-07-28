package com.example.exchangeRateViewer.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exchangeRateViewer.entity.RateDetails;
import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.RateModel;
import com.example.exchangeRateViewer.service.ExchangeRateDataService;
import com.example.exchangeRateViewer.service.ExchangeRateViewService;


@Service
public class ExchangeRateViewServiceImpl implements ExchangeRateViewService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExchangeRateViewServiceImpl.class);
	
	@Autowired
	private ExchangeRateDataService dataService;
	@Override
	public ExchangeRateModel getExchangeRatesByDate(String date) {
		
		LOG.info("ExchangeRateViewServiceImpl :: getExchangeRatesByDate");
		ExchangeRateModel model =  new ExchangeRateModel();
		List<RateDetails> rates = dataService.getExchangeRatesByDate(Date.valueOf(date));
		List<RateModel> rateModelList = new ArrayList<>();
		rates.forEach(r-> {
			
			RateModel rateModel =  new RateModel();
			rateModel.setCurrencyCode(r.getCurrency());
			rateModel.setDate(r.getDate().toString());
			rateModel.setExChangeRate( r.getRate());
			rateModelList.add(rateModel);
			model.setBase(r.getBase());
			
			
		});
		
		model.setRates(rateModelList);
		
		return model;
	}
	
	
	 
	
	

}
