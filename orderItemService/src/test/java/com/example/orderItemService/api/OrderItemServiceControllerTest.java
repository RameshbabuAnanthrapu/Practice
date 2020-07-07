package com.example.orderItemService.api;

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

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.exception.OrderItemServiceExceptionController;
import com.example.orderItemService.mapper.service.OrderItemMapperService;
import com.example.orderItemService.model.OrderItemModel;
import com.example.orderItemService.model.OrderItemServiceResponse;
import com.example.orderItemService.service.OrderItemDataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderItemServiceController.class)
public class OrderItemServiceControllerTest {

	@MockBean
	private OrderItemMapperService mapperService;

	@MockBean
	private OrderItemDataService dataService;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderItemServiceExceptionController ex;

	@Test
	public void testCreateOrderItem() throws JsonProcessingException, Exception {

		Mockito.when(mapperService.mapModelToEntity(ArgumentMatchers.anyList())).thenReturn(buildOrderItems());
		Mockito.when(dataService.createOrderItem(ArgumentMatchers.anyList())).thenReturn(buildOrderItems());
		Mockito.when(mapperService.mapEntityToModel(ArgumentMatchers.anyList())).thenReturn(buildOrderItemModel());

		MvcResult result = mockMvc.perform(post("/orderItem-management/managed-orderItems")
				.content(getMapper().writeValueAsString(buildOrderItems()))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andReturn();
		String responseString = result.getResponse().getContentAsString();
		OrderItemServiceResponse itemServiceRes = getMapper().readValue(responseString, OrderItemServiceResponse.class);
		Assert.assertEquals("Test", itemServiceRes.getOrderItems().get(0).getProductName());

	}

	@Test
	public void testGetAllItems() throws Exception {
		Mockito.when(dataService.fetchOrderItems()).thenReturn(buildOrderItems());

		MvcResult result = mockMvc.perform(get("/orderItem-management/managed-orderItems")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andReturn();

		String responseString = result.getResponse().getContentAsString();
		OrderItemServiceResponse itemServiceRes = getMapper().readValue(responseString, OrderItemServiceResponse.class);
		Assert.assertEquals(1, itemServiceRes.getOrderItems().size());

	}

	@Test
	public void testGetItemById() throws Exception {
		
		
		Mockito.when(mapperService.mapModelToEntity(ArgumentMatchers.anyList())).thenReturn(buildOrderItems());

		Mockito.when(dataService.fetchOrderItemById(ArgumentMatchers.anyLong())).thenReturn(buildOrderItems());
		Mockito.when(mapperService.mapEntityToModel(ArgumentMatchers.anyList())).thenReturn(buildOrderItemModel());

		MvcResult result = mockMvc.perform(get("/orderItem-management/managed-orderItems/1")
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)).andReturn();

		String responseString = result.getResponse().getContentAsString();
		OrderItemServiceResponse itemServiceRes = getMapper().readValue(responseString, OrderItemServiceResponse.class);
		Assert.assertEquals(1, itemServiceRes.getOrderItems().size());

	}

	@Test
	public void testCreateOrderItem_Validator() throws JsonProcessingException, Exception {

		OrderItemServiceResponse response = new OrderItemServiceResponse();
		response.setErrorResponse("MethodArgumentNotValidException");
		ResponseEntity<OrderItemServiceResponse> resEntity = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		Mockito.when(
				ex.handleMethodArgumentNotValidException(ArgumentMatchers.any(MethodArgumentNotValidException.class)))
				.thenReturn(resEntity);
		List<OrderItemModel> emtyList = new ArrayList<>();
		OrderItemModel emptyModel = new OrderItemModel();
		emtyList.add(emptyModel);

		mockMvc.perform(
				post("/orderItem-management/managed-orderItems").content(getMapper().writeValueAsString(emptyModel))
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	private List<OrderItemModel> buildOrderItemModel() {

		List<OrderItemModel> modelList = new ArrayList<>();
		OrderItemModel model = new OrderItemModel();
		model.setProductCode(10);
		model.setOrderId(1);
		model.setProductName("Test");
		modelList.add(model);
		return modelList;

	}

	private List<OrderItem> buildOrderItems() {
		OrderItem item = new OrderItem();
		item.setOrderId(1);
		item.setProductCode(10);
		item.setProductName("Test");
		List<OrderItem> items = new ArrayList<>();
		items.add(item);
		return items;
	}

	public ObjectMapper getMapper() {

		return new ObjectMapper();
	}

}
