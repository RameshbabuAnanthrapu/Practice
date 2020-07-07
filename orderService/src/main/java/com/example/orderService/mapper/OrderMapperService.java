package com.example.orderService.mapper;

import com.example.orderService.dao.OrderDetails;
import com.example.orderService.model.OrderModel;

public interface OrderMapperService {

	OrderDetails mapModelToEntity(OrderModel order);

	OrderModel mapEntityToModel(OrderDetails orderFromDB);

}
