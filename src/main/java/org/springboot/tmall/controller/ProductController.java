package org.springboot.tmall.controller;

import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.service.ProductService;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired ProductService productService;
	
	@GetMapping("/products/{id}")
	public Product get(@PathVariable int id) {
		return productService.get(id);
	}
	
	@GetMapping("/categories/{cid}/products")
	public Page4Navigator<Product> list(@PathVariable int cid,
@RequestParam(value = "start",defaultValue = "0")int start,
@RequestParam(value = "size",defaultValue = "5")int size){
		return productService.list(cid, start, size, 5);
	}
	
	@PostMapping("/products")
	public Product add(@RequestBody Product product) {
		return productService.add(product);
	}
	@PutMapping("/products")
	public Product update(@RequestBody Product product) {
		return productService.add(product);
	}
	
	@DeleteMapping("/products/{id}")
	public String delete(@PathVariable int id) {
		return productService.delete(id);
	}
	
	
	
}
