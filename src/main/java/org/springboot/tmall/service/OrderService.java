package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.OrderDao;
import org.springboot.tmall.pojo.Order;
import org.springboot.tmall.pojo.OrderItem;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	public static final String waitPay="waitPay";
	public static final String waitDelivery="waitDelivery";
	public static final String waitComfirm="waitComfirm";
	public static final String waitReview="waitReview";
	public static final String finish="finish";
	public static final String delete="delete";
	
	@Autowired OrderDao orderDao;
	public Page4Navigator<Order> list(int start,int size,int navigatePages){
		Sort sort=new Sort(Sort.Direction.DESC, "id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Page<Order> page=orderDao.findAll(pageable);
		return new Page4Navigator<Order>(page, navigatePages);
		
	}
	
	public void removeOrderFromOrderItems(List<Order> orders) {
		for(Order order:orders) {
			removeOrderFromOrderItem(order);
		}
	}
	private void removeOrderFromOrderItem(Order order){
		List<OrderItem> orderItems=order.getOrderItems();
		for(OrderItem orderItem:orderItems) {
			orderItem.setOrder(null);
		}
	}
	public Order get(int id) {
		return orderDao.getOne(id);
	}
	
	public void update(Order order) {
		orderDao.save(order);
		
	}
}
