package com.example.orderService.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.orderService.model.OrderItemModel;
import com.example.orderService.model.OrderItemServiceResponse;


@FeignClient(name="orderItesm-service",url = "${orderItem.service.uri}")
public interface OrderItemProxyService {

	@RequestMapping(method = RequestMethod.POST, value = "/item/create")
	ResponseEntity<OrderItemServiceResponse>  createItem(@RequestBody List<OrderItemModel> itemList);
	
	@RequestMapping(method = RequestMethod.GET, value="/item/getItemById/{orderId}")
	ResponseEntity<OrderItemServiceResponse> getItems(@PathVariable(name="orderId") long id);

}
