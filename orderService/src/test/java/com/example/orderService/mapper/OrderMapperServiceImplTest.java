package com.example.orderService.mapper;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.model.OrderModel;

@RunWith(SpringRunner.class)
public class OrderMapperServiceImplTest {
	
	@InjectMocks
	private OrderMapperServiceImpl orderMapperService;
	
	
	@Test
	public void testMapModelToEntity(){
		
		OrderModel model =  new OrderModel();
		model.setCustomerName("Test");
		model.setShippingAddress("test");
		model.setOrderDate(new Date());
		OrderDetails details = orderMapperService.mapModelToEntity(model);
		Assert.assertEquals("Test", details.getCustomerName());
		
		
		
	}
	
	@Test
	public void testMapEntityToModel(){
		
		OrderDetails detail =  new OrderDetails();
		detail.setCustomerName("Test");
		detail.setShippingAddress("test");
		detail.setOrderDate(new Date());
		OrderModel model = orderMapperService.mapEntityToModel(detail);
		Assert.assertEquals("Test", model.getCustomerName());
		
		
		
	}

}
