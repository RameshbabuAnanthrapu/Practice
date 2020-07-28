package com.example.exchangeRateViewer.fallBack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.exchangeRateViewer.model.ExternalApiResponse;
import com.example.exchangeRateViewer.service.RateApiProxyService;



@Service
public class RateApiProxyFallBackImpl implements RateApiProxyService {

	private static final Logger LOG = LoggerFactory.getLogger(RateApiProxyFallBackImpl.class);
	@Override
	public ExternalApiResponse getExchangeRatesByDate(String userAgent, String symbols, String date) {
		LOG.info("Hystrix Invoked FallBack Method");
		return new ExternalApiResponse();
	}

}
