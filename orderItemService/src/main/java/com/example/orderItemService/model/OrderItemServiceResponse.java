package com.example.orderItemService.model;

import java.util.List;


public class OrderItemServiceResponse {
	
	private List<OrderItemModel> orderItems;
	private String errorResponse;
	
	public List<OrderItemModel> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemModel> orderItems) {
		this.orderItems = orderItems;
	}
	public String getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	
	

}
