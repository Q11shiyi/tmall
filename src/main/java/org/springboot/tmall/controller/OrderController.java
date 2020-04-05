package org.springboot.tmall.controller;

import java.util.Date;

import org.springboot.tmall.pojo.Order;
import org.springboot.tmall.service.OrderItemService;
import org.springboot.tmall.service.OrderService;
import org.springboot.tmall.util.Page4Navigator;
import org.springboot.tmall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	@Autowired OrderService orderService;
	@Autowired OrderItemService orderItemService;
	@GetMapping("/orders")
	public Page4Navigator<Order> list(@RequestParam(value = "start",defaultValue = "0")int start
,@RequestParam(value = "size",defaultValue = "5") int size){
		start=start<0?0:start;
		Page4Navigator<Order> page=orderService.list(start, size, 5);
		//将order表中对应的orderItem数据注入
		orderItemService.fill(page.getContent());
		//将orderitem中的order属性设置为空,防止转换为json数据时循环报错
		orderService.removeOrderFromOrderItems(page.getContent());
		return page;
	}
	
	@PutMapping("deliveryOrder/{oid}")
	public Object deliveryOrder(@PathVariable int oid) {
		Order order=orderService.get(oid);
		order.setDeliveryDate(new Date());
		order.setStatus(OrderService.waitComfirm);
		orderService.update(order);
		return Result.success();
	}
}
