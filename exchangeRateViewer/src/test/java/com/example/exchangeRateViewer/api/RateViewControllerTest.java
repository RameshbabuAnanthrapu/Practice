package com.example.exchangeRateViewer.api;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.exchangeRateViewer.model.ExchangeRateModel;
import com.example.exchangeRateViewer.model.ExchangeRateViewResponse;
import com.example.exchangeRateViewer.model.RateModel;
import com.example.exchangeRateViewer.service.ExchangeRateLoadService;
import com.example.exchangeRateViewer.service.ExchangeRateViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@WebMvcTest(RateViewController.class)
public class RateViewControllerTest {
	
	@MockBean
	private ExchangeRateLoadService loadService;

	@MockBean
	private ExchangeRateViewService viewService;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void  testLoadExchangeRates() throws JsonProcessingException, Exception {
		
		ExchangeRateModel model = new ExchangeRateModel();
		model.setBase("EUR");
		Mockito.when(loadService.uploadExchangeRates(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(model);
		
		MvcResult mvcResult = mockMvc
				.perform(get("/api/rates").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		
		
		ExchangeRateViewResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("EUR", serviceRespopnse.getExchangeRateInfo().getBase());
		
	}
	
	@Test
	public void testGetExchangeRates() throws JsonProcessingException, Exception {
		
		ExchangeRateModel model = new ExchangeRateModel();
		model.setBase("EUR");
		List<RateModel> rateModelList = new ArrayList<>();
		RateModel rateModel = new RateModel();
		rateModel.setCurrencyCode("USD");
		rateModel.setExChangeRate(new BigDecimal(0.90));
		rateModelList.add(rateModel);
		model.setRates(rateModelList);
		Mockito.when(viewService.getExchangeRatesByDate(ArgumentMatchers.anyString())).thenReturn(model);
		MvcResult mvcResult = mockMvc
				.perform(get("/api/rates/2020-06-01").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		ExchangeRateViewResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("USD", serviceRespopnse.getExchangeRateInfo().getRates().get(0).getCurrencyCode());
		
	}
	
	@Test
	public void testGetExchangeRates_IllegalArgumentException() throws JsonProcessingException, Exception {
		
		ExchangeRateModel model = new ExchangeRateModel();
		model.setBase("EUR");
		List<RateModel> rateModelList = new ArrayList<>();
		RateModel rateModel = new RateModel();
		rateModel.setCurrencyCode("USD");
		rateModel.setExChangeRate(new BigDecimal(0.90));
		rateModelList.add(rateModel);
		model.setRates(rateModelList);
		//Mockito.when(viewService.getExchangeRatesByDate(ArgumentMatchers.anyString())).thenReturn(model);
		MvcResult mvcResult = mockMvc
				.perform(get("/api/rates/test").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		
		ExchangeRateViewResponse serviceRespopnse = parseMvcResult(mvcResult);
		Assert.assertEquals("Invalid input parameter  :test", serviceRespopnse.getErrorMessage());
		
	}
	
	private ObjectMapper getObjectMapper() {

		return new ObjectMapper();
	}
	
	private ExchangeRateViewResponse  parseMvcResult(MvcResult mvcResult) throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		
		String resultJson = mvcResult.getResponse().getContentAsString();
		return getObjectMapper().readValue(resultJson, ExchangeRateViewResponse.class);
		
		
	}

}
