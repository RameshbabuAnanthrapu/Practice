package com.example.orderService.service;

import com.example.orderService.dao.OrderDetails;

public interface OrderDataService {
	
	OrderDetails createOrder(OrderDetails order);
	OrderDetails getOrder(long orderId);

}
