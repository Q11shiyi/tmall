package org.springboot.tmall.dao;

import java.util.List;

import org.springboot.tmall.pojo.Order;
import org.springboot.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>{
	List<OrderItem> findByOrderOrderByIdDesc(Order order);
}
