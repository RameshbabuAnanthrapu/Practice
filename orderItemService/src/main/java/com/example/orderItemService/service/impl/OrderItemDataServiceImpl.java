package com.example.orderItemService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.exception.OrderItemServiceException;
import com.example.orderItemService.repo.OrderItemRepository;
import com.example.orderItemService.service.OrderItemDataService;

@Service
public class OrderItemDataServiceImpl implements OrderItemDataService {

	@Autowired
	private OrderItemRepository itemRepo;

	@Override
	public List<OrderItem> createOrderItem(List<OrderItem> item) {
		List<OrderItem> itemsList = new ArrayList<>();

		item.forEach(i -> itemsList.add(itemRepo.save(i)));

		return itemsList;
	}

	@Override
	public List<OrderItem> fetchOrderItems() {
		List<OrderItem> itemList = itemRepo.findAll();
		return itemList;
	}

	@Override
	public List<OrderItem> fetchOrderItemById(long prodId) {
		List<OrderItem> item = itemRepo.findItemsByOrderID(prodId);
		if(CollectionUtils.isEmpty(item)) {
			
			throw new OrderItemServiceException("No Items Found for Order "+prodId, HttpStatus.NOT_FOUND);
		}
		return item;
	}

}
