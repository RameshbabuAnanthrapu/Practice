package com.example.orderService.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.exception.OrderServiceException;
import com.example.orderService.repo.OrderRepository;
import com.example.orderService.service.OrderDataService;


@Service
public class OrderDataServiceImpl implements OrderDataService  {

	@Autowired
	private OrderRepository orderRepo;
	
	@Override
	public OrderDetails createOrder(OrderDetails order) {
		OrderDetails orderInfo = orderRepo.saveAndFlush(order);
		return orderInfo;
	}

	@Override
	public OrderDetails getOrder(long orderId) {
		Optional<OrderDetails> orderRecord = orderRepo.findById(orderId);
		if(!orderRecord.isPresent()) {
			throw new OrderServiceException("Record Not found for Order ID "+orderId, HttpStatus.NOT_FOUND);
		}
		return orderRecord.orElse(new OrderDetails());
	}

}
