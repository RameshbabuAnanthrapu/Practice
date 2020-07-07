package com.example.orderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.orderService.model.OrderServiceResponse;

@ControllerAdvice
public class OrderServiceExceptionController {
	
	
	
	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<OrderServiceResponse> handleOrderServiceException(OrderServiceException ex){
		
		OrderServiceResponse res = new OrderServiceResponse();
		res.setErrorresponse(ex.getMessage());
		
		return new ResponseEntity<>(res, ex.getStatus());
		
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<OrderServiceResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		OrderServiceResponse res = new OrderServiceResponse();
		res.setErrorresponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		
		
	}

}
