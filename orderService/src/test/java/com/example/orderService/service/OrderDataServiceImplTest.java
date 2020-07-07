package com.example.orderService.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.exception.OrderServiceException;
import com.example.orderService.repo.OrderRepository;
import com.example.orderService.service.impl.OrderDataServiceImpl;

@RunWith(SpringRunner.class)
public class OrderDataServiceImplTest {

	@InjectMocks
	private OrderDataServiceImpl orderDataService;

	@Mock
	private OrderRepository repo;

	@Test
	public void testCreateOrder() {

		OrderDetails details = new OrderDetails();
		details.setCustomerName("Test");
		Mockito.when(repo.saveAndFlush(ArgumentMatchers.any(OrderDetails.class))).thenReturn(details);
		OrderDetails response = orderDataService.createOrder(details);
		Assert.assertEquals("Test", response.getCustomerName());

	}

	@Test
	public void testGetOrder() {

		OrderDetails details = new OrderDetails();
		details.setCustomerName("Test");
		Mockito.when(repo.saveAndFlush(ArgumentMatchers.any(OrderDetails.class))).thenReturn(details);
		OrderDetails response = orderDataService.createOrder(details);
		Assert.assertEquals("Test", response.getCustomerName());

	}

	@Test(expected = OrderServiceException.class)
	public void testGetOrder_OrderServiceException() {

		OrderDetails details = new OrderDetails();
		details.setCustomerName("Test");
		Mockito.when(repo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		orderDataService.getOrder(1);

	}
}
