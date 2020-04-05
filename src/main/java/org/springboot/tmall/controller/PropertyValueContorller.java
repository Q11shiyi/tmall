package org.springboot.tmall.controller;

import java.util.List;

import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.PropertyValue;
import org.springboot.tmall.service.ProductService;
import org.springboot.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyValueContorller {

	@Autowired ProductService productService;
	@Autowired PropertyValueService propertyValueService;
	
	@GetMapping("products/{pid}/propertyValues")
	public List<PropertyValue> list(@PathVariable int pid){
		Product product=productService.get(pid);
		propertyValueService.init(product);
		return propertyValueService.findByProduct(product);
	}
	@PutMapping("propertyValues")
	public PropertyValue update(@RequestBody PropertyValue propertyValue) {
		return propertyValueService.update(propertyValue);
	}
}
