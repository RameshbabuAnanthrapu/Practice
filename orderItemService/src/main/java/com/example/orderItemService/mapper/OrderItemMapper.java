package com.example.orderItemService.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.model.OrderItemModel;

@Component
public class OrderItemMapper {

	public OrderItem mapModel(OrderItemModel model){
		
		ModelMapper mapper = new ModelMapper();
		OrderItem  item = mapper.map(model, OrderItem.class);
		
		return item;
		
	}
	
public OrderItemModel mapEntity(OrderItem item){
		
		ModelMapper mapper = new ModelMapper();
		OrderItemModel  itemModel = mapper.map(item, OrderItemModel.class);
		
		return itemModel;
		
	}
}
