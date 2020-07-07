package com.example.orderItemService.mapper.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import com.example.orderItemService.dao.OrderItem;

import com.example.orderItemService.model.OrderItemModel;

@Service
public class OrderItemMapperServiceImpl implements OrderItemMapperService{
	
	

	@Override
	public List<OrderItem> mapModelToEntity(List<OrderItemModel> items) {
		List<OrderItem> itemList =  items.stream().map(model -> getModelMapper().map(model, OrderItem.class)).collect(Collectors.toList());
		return itemList;
	}

	@Override
	public List<OrderItemModel> mapEntityToModel(List<OrderItem> itemList) {
		itemList.forEach(i1 -> System.out.println("prod code before map"+ i1.getProductCode()));
		List<OrderItemModel> modelList =  itemList.stream().map(item -> getModelMapper().map(item, OrderItemModel.class)).collect(Collectors.toList());
		
		modelList.forEach(i2 -> System.out.println("prod code after map"+ i2.getProductCode()));
		return modelList;
	}
	
	private ModelMapper getModelMapper() {
		
		return new ModelMapper();
	}
	

}
