package com.example.orderItemService.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.orderItemService.dao.OrderItem;



@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	@Query("select o from OrderItem o where o.orderId = :id")
	List<OrderItem> findItemsByOrderID(@Param("id") long id);

}
