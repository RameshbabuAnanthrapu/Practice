package com.example.orderService.api;

import java.util.ArrayList;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.exception.OrderServiceExceptionController;
import com.example.orderService.mapper.OrderMapperService;

import com.example.orderService.model.OrderItemModel;
import com.example.orderService.model.OrderItemServiceResponse;
import com.example.orderService.model.OrderModel;
import com.example.orderService.model.OrderServiceResponse;
import com.example.orderService.service.OrderDataService;
import com.example.orderService.service.OrderItemProxyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderItemProxyService itemProxy;

	@MockBean
	private OrderMapperService mapperService;

	@MockBean
	private OrderDataService dataService;

	@MockBean
	private OrderServiceExceptionController ex;

	@Test
	public void testcreateOrder() throws JsonProcessingException, Exception {
		OrderModel model = buildOrderModel();

		OrderDetails details = buildOrderDetails();

		OrderItemServiceResponse itemsServiceResponse = new OrderItemServiceResponse();
		itemsServiceResponse.setOrderItems(model.getItems());

		ResponseEntity<OrderItemServiceResponse> mockItemResponse = new ResponseEntity<>(itemsServiceResponse,
				HttpStatus.OK);

		Mockito.when(mapperService.mapModelToEntity(ArgumentMatchers.any(OrderModel.class))).thenReturn(details);
		Mockito.when(dataService.createOrder(ArgumentMatchers.any(OrderDetails.class))).thenReturn(details);
		Mockito.when(mapperService.mapEntityToModel(ArgumentMatchers.any(OrderDetails.class))).thenReturn(model);
		Mockito.when(itemProxy.createItem(ArgumentMatchers.anyList())).thenReturn(mockItemResponse);

		MvcResult mvcResult = mockMvc
				.perform(post("/order-management/orders").content(getObjectMapper().writeValueAsString(model))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();

		String resultJson = mvcResult.getResponse().getContentAsString();
		OrderServiceResponse serviceRespopnse = getObjectMapper().readValue(resultJson, OrderServiceResponse.class);
		Assert.assertEquals("TestCustomer", serviceRespopnse.getOrders().getCustomerName());

	}

	@Test
	public void testcreateOrder_Validator() throws JsonProcessingException, Exception {
		OrderModel emtyObject = new OrderModel();

		OrderServiceResponse orderResponse = new OrderServiceResponse();

		orderResponse.setErrorresponse("MethodArgumentNotValidException");

		ResponseEntity<OrderServiceResponse> mockItemResponse = new ResponseEntity<>(orderResponse,
				HttpStatus.BAD_REQUEST);

		Mockito.when(
				ex.handleMethodArgumentNotValidException(ArgumentMatchers.any(MethodArgumentNotValidException.class)))
				.thenReturn(mockItemResponse);

		mockMvc.perform(
				post("/order-management/orders").content(getObjectMapper().writeValueAsString(emtyObject))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void testGetOrderById() throws JsonProcessingException, Exception {

		OrderDetails details = buildOrderDetails();
		OrderModel model = buildOrderModel();
		OrderItemServiceResponse itemServiceResponse = new OrderItemServiceResponse();
		itemServiceResponse.setOrderItems(model.getItems());
		ResponseEntity<OrderItemServiceResponse> itemResponse = new ResponseEntity<>(itemServiceResponse,
				HttpStatus.OK);
		Mockito.when(dataService.getOrder(ArgumentMatchers.anyLong())).thenReturn(details);
		Mockito.when(itemProxy.getItems(ArgumentMatchers.anyLong())).thenReturn(itemResponse);
		Mockito.when(mapperService.mapEntityToModel(ArgumentMatchers.any(OrderDetails.class))).thenReturn(model);
		MvcResult mvcResult = mockMvc.perform(
				get("/order-management/orders/1").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andReturn();
		String resultJson = mvcResult.getResponse().getContentAsString();
		OrderServiceResponse serviceRespopnse = getObjectMapper().readValue(resultJson, OrderServiceResponse.class);
		Assert.assertEquals(1L, serviceRespopnse.getOrders().getOrderId());
	}

	

	private OrderModel buildOrderModel() {

		OrderModel model = new OrderModel();
		model.setOrderId(1);
		model.setCustomerName("TestCustomer");
		model.setShippingAddress("Test Address");
		OrderItemModel item1 = new OrderItemModel();
		item1.setProductCode(1);
		item1.setProductName("testProd");
		item1.setQuantity(1);
		List<OrderItemModel> itemList = new ArrayList<>();
		itemList.add(item1);
		model.setItems(itemList);

		return model;

	}

	private OrderDetails buildOrderDetails() {

		OrderDetails details = new OrderDetails();
		details.setOrderId(1L);
		details.setCustomerName("TestCustomer");
		details.setShippingAddress("Test Address");
		return details;
	}

	private ObjectMapper getObjectMapper() {

		return new ObjectMapper();
	}

}
