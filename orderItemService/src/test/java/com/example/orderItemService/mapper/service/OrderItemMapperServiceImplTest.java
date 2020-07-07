package com.example.orderItemService.mapper.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.model.OrderItemModel;

@RunWith(SpringRunner.class)
public class OrderItemMapperServiceImplTest {

	@InjectMocks
	private OrderItemMapperServiceImpl orderItemMapperService;

	@Test
	public void testMapModelToEntity() {

		OrderItemModel model = new OrderItemModel();
		model.setOrderId(1);
		model.setProductCode(10);
		model.setProductName("test");
		List<OrderItemModel> modelList = new ArrayList<>();
		modelList.add(model);

		List<OrderItem> details = orderItemMapperService.mapModelToEntity(modelList);
		Assert.assertEquals("test", details.get(0).getProductName());

	}

	@Test
	public void testMapEntityToModel() {

		OrderItem item = new OrderItem();
		item.setOrderId(1);
		item.setProductCode(10);
		item.setProductName("test");
		List<OrderItem> itemList = new ArrayList<>();
		itemList.add(item);
		List<OrderItemModel> modelList = orderItemMapperService.mapEntityToModel(itemList);
		Assert.assertEquals("test", modelList.get(0).getProductName());

	}

}
