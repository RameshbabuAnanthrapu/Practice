package com.example.orderItemService.service;

import java.util.List;

import com.example.orderItemService.dao.OrderItem;

public interface OrderItemDataService {

	
	List<OrderItem> createOrderItem(List<OrderItem> item);
	List<OrderItem> fetchOrderItems();
	List<OrderItem> fetchOrderItemById(long prodId);
	
}
