package com.example.orderItemService.api;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.orderItemService.dao.OrderItem;
import com.example.orderItemService.mapper.service.OrderItemMapperService;
import com.example.orderItemService.model.OrderItemModel;
import com.example.orderItemService.model.OrderItemServiceResponse;
import com.example.orderItemService.service.OrderItemDataService;

@RestController
@RequestMapping("/item")
public class OrderItemServiceController {

	@Autowired
	private OrderItemMapperService mapperService;

	@Autowired
	private OrderItemDataService dataService;

	@PostMapping("/create")
	public ResponseEntity<OrderItemServiceResponse> createOrderItem(@Valid @RequestBody List<OrderItemModel> items) {
		

		List<OrderItem> itemList = dataService.createOrderItem(mapperService.mapModelToEntity(items));

		List<OrderItemModel> reponseModel = mapperService.mapEntityToModel(itemList);
		
		OrderItemServiceResponse response = new OrderItemServiceResponse();
		response.setOrderItems(reponseModel);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/getAllItems")
	public ResponseEntity<OrderItemServiceResponse> getAllItems() {
		List<OrderItemModel> itemList = mapperService.mapEntityToModel(dataService.fetchOrderItems());
		OrderItemServiceResponse response = new OrderItemServiceResponse();
		response.setOrderItems(itemList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getItemById/{orderId}")
	public ResponseEntity<OrderItemServiceResponse> getItemById(@NotNull @NotEmpty @PathVariable(name = "orderId") Long id) {
		
		List<OrderItemModel> itemModelList = mapperService.mapEntityToModel(dataService.fetchOrderItemById(id));
		
		OrderItemServiceResponse response = new OrderItemServiceResponse();
		response.setOrderItems(itemModelList);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
