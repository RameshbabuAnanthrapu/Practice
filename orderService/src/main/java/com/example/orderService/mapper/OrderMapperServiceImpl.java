package com.example.orderService.mapper;


import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.model.OrderModel;

@Service
public class OrderMapperServiceImpl implements OrderMapperService{
	
	

	
	
	private ModelMapper getModelMapper() {
		
		return new ModelMapper();
	}

	@Override
	public OrderDetails mapModelToEntity(OrderModel order) {
		OrderDetails details = getModelMapper().map(order, OrderDetails.class);
		return details;
	}

	@Override
	public OrderModel mapEntityToModel(OrderDetails ordersFromDB) {
		OrderModel order = getModelMapper().map(ordersFromDB, OrderModel.class);
		return order;
	}
	

}
