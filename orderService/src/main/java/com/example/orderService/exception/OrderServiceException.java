package com.example.orderService.exception;

import org.springframework.http.HttpStatus;

public class OrderServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;

	public OrderServiceException(String message, HttpStatus status) {
		
		super(message);
		this.message = message;
		this.status = status;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	

}
