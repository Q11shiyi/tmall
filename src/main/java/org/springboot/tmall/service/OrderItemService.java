package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.OrderItemDao;
import org.springboot.tmall.pojo.Order;
import org.springboot.tmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
	@Autowired OrderItemDao orderItemDao;
	@Autowired ProductImageService productImageService;
	
	public void fill(List<Order> orders) {
		for(Order order:orders) {
			fill(order);
		}
	}
	public void fill(Order order){
		List<OrderItem> orderItems=listByOrder(order);
		float total=0;
		int totalNumber=0;
		for(OrderItem oi:orderItems) {
			total+=oi.getNumber()*oi.getProduct().getPromotePrice();
			totalNumber+=oi.getNumber();
			productImageService.setFirstProductImage(oi.getProduct());
		}
		order.setTotal(total);
		order.setTotalNumber(totalNumber);
		order.setOrderItems(orderItems);
	}
	
	public List<OrderItem> listByOrder(Order order){
		return orderItemDao.findByOrderOrderByIdDesc(order);
	}
	
}
