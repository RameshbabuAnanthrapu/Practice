package com.example.orderService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.orderService.dao.OrderDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Long> {

}
