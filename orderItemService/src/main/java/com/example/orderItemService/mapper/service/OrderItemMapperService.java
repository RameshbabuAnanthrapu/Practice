package com.example.orderItemService.mapper.service;

import java.util.List;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.model.OrderItemModel;

public interface OrderItemMapperService {
	
	List<OrderItem> mapModelToEntity(List<OrderItemModel> items);
	List<OrderItemModel> mapEntityToModel(List<OrderItem> itemList);

}
