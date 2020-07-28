package com.example.exchangeRateViewer.model;

public class ExchangeRateViewResponse {

	private ExchangeRateModel exchangeRateInfo;

	private String errorMessage;

	public ExchangeRateModel getExchangeRateInfo() {
		return exchangeRateInfo;
	}

	public void setExchangeRateInfo(ExchangeRateModel exchangeRateInfo) {
		this.exchangeRateInfo = exchangeRateInfo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
