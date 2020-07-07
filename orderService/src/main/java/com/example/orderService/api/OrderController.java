package com.example.orderService.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.orderService.dao.OrderDetails;

import com.example.orderService.mapper.OrderMapperService;
import com.example.orderService.model.OrderItemModel;
import com.example.orderService.model.OrderItemServiceResponse;
import com.example.orderService.model.OrderModel;
import com.example.orderService.model.OrderServiceResponse;
import com.example.orderService.service.OrderDataService;
import com.example.orderService.service.OrderItemProxyService;

@RestController
@RequestMapping("/order-management")
public class OrderController {

	@Autowired
	private OrderItemProxyService itemproxy;

	@Autowired
	private OrderMapperService mapperService;

	@Autowired
	private OrderDataService dataService;

	@PostMapping("/manage-orders")
	public ResponseEntity<OrderServiceResponse> createOrder(@Valid @RequestBody OrderModel orderModel) {

		OrderDetails order = dataService.createOrder(mapperService.mapModelToEntity(orderModel));
		order.setOrderDate(new Date());

		OrderModel model = mapperService.mapEntityToModel(order);
		List<OrderItemModel> itemList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(orderModel.getItems())) {

			orderModel.getItems().forEach(item -> item.setOrderId(order.getOrderId()));
			ResponseEntity<OrderItemServiceResponse> orderItemServiceresponse = itemproxy
					.createItem(orderModel.getItems());
			itemList = orderItemServiceresponse.getBody().getOrderItems();

		}

		model.setItems(itemList);

		OrderServiceResponse response = new OrderServiceResponse();
		response.setOrders(model);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/manage-orders/{orderId}")
	public ResponseEntity<OrderServiceResponse> getOrderById(@NotEmpty @PathVariable(name = "orderId") long id) {
		OrderServiceResponse response = new OrderServiceResponse();
		OrderDetails order = dataService.getOrder(id);
		ResponseEntity<OrderItemServiceResponse> itemServiceResponse = itemproxy.getItems(id);
		List<OrderItemModel> items = new ArrayList<>();
		if (null != itemServiceResponse.getBody()
				&& StringUtils.isEmpty(itemServiceResponse.getBody().getErrorResponse())) {
			items = itemServiceResponse.getBody().getOrderItems();
		} else {
			response.setErrorresponse(itemServiceResponse.getBody().getErrorResponse());
		}

		OrderModel orderModel = mapperService.mapEntityToModel(order);
		orderModel.setItems(items);

		response.setOrders(orderModel);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
