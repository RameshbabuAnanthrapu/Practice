package com.example.exchangeRateViewer.exception;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.exchangeRateViewer.model.ExchangeRateViewResponse;

@ControllerAdvice
public class ApplicationExceptionController {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExchangeRateViewResponse> handleApplicationException(ApplicationException ex) {

		ExchangeRateViewResponse res = new ExchangeRateViewResponse();
		res.setErrorMessage(ex.getMessage());

		return new ResponseEntity<>(res, ex.getStatus());

	}

}
