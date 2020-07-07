package com.example.orderService.model;

public class OrderServiceResponse {

	private OrderModel orders;
	private String errorresponse;

	public OrderModel getOrders() {
		return orders;
	}

	public void setOrders(OrderModel orders) {
		this.orders = orders;
	}

	public String getErrorresponse() {
		return errorresponse;
	}

	public void setErrorresponse(String errorresponse) {
		this.errorresponse = errorresponse;
	}

}
