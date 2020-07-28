package com.example.exchangeRateViewer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.exchangeRateViewer.fallBack.RateApiProxyFallBackImpl;
import com.example.exchangeRateViewer.model.ExternalApiResponse;





  @FeignClient(name="external-rates-api",url = "${rate.api.uri}", fallback = RateApiProxyFallBackImpl.class) 
  public  interface RateApiProxyService {
  
  @RequestMapping(method = RequestMethod.GET, value = "/api/{date}")  
  ExternalApiResponse getExchangeRatesByDate(@RequestHeader(name = "user-agent") String userAgent, @RequestParam(value = "symbols") String symbols, @PathVariable(value = "date") String date);
  
  }
 
  
 