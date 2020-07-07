package com.example.orderItemService.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.exception.OrderItemServiceException;
import com.example.orderItemService.repo.OrderItemRepository;
import com.example.orderItemService.service.impl.OrderItemDataServiceImpl;

@RunWith(SpringRunner.class)
public class OrderItemDataServiceImplTest {

	@InjectMocks
	private OrderItemDataServiceImpl orderItemDataService;

	@Mock
	private OrderItemRepository repo;

	@Test
	public void testCreateItem() {

		Mockito.when(repo.saveAndFlush(ArgumentMatchers.any(OrderItem.class))).thenReturn(buildItems().get(0));

		List<OrderItem> resItems = orderItemDataService.createOrderItem(buildItems());
		Assert.assertEquals(1, resItems.size());
	}

	@Test
	public void testFetchOrderItems() {

		Mockito.when(repo.findAll()).thenReturn(buildItems());
		List<OrderItem> resItems = orderItemDataService.fetchOrderItems();
		Assert.assertEquals(1, resItems.size());

	}

	@Test
	public void testFetchOrderItemById() {

		Mockito.when(repo.findItemsByOrderID(ArgumentMatchers.anyLong())).thenReturn(buildItems());
		List<OrderItem> resItems = orderItemDataService.fetchOrderItemById(1l);
		Assert.assertEquals(1, resItems.size());
	}
	
	@Test(expected = OrderItemServiceException.class)
	public void testFetchOrderItemById_Ex() {

		Mockito.when(repo.findItemsByOrderID(ArgumentMatchers.anyLong())).thenReturn(null);
		orderItemDataService.fetchOrderItemById(1l);
		
	}

	private List<OrderItem> buildItems() {

		OrderItem item = new OrderItem();
		item.setOrderId(1);
		item.setProductCode(10);
		item.setProductName("test");
		List<OrderItem> itemList = new ArrayList<>();
		itemList.add(item);
		return itemList;
	}

}
