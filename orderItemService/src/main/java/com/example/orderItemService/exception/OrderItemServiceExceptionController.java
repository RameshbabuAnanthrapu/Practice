package com.example.orderItemService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.orderItemService.model.OrderItemServiceResponse;

public class OrderItemServiceExceptionController {
	
	
	@ExceptionHandler(OrderItemServiceException.class)
	public ResponseEntity<OrderItemServiceResponse> handleOrderItemServiceException(OrderItemServiceException ex){
		
		OrderItemServiceResponse response =  new OrderItemServiceResponse();
		response.setErrorResponse(ex.getMessage());
		return new ResponseEntity<>(response,ex.getStatus());
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<OrderItemServiceResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		OrderItemServiceResponse res = new OrderItemServiceResponse();
		res.setErrorResponse(ex.getLocalizedMessage());
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		
		
	}

}
