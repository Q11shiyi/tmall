package org.springboot.tmall.controller;

import org.springboot.tmall.pojo.Property;
import org.springboot.tmall.service.PropertyService;
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
public class PropertyController {
	
	@Autowired PropertyService propertyService;
	
	@GetMapping("properties/{id}")
	public Property get(@PathVariable int id) {
		return propertyService.get(id);
	}
	
	@GetMapping("/categories/{cid}/properties")
	public Page4Navigator<Property> list(@PathVariable() int cid,
@RequestParam(value = "start",defaultValue = "0") int start,
@RequestParam(value = "size",defaultValue = "5") int size){
		return propertyService.list(cid, start, size, 5);
	}
	
	@PostMapping("/properties")
	public Property add(@RequestBody Property property) {
		return propertyService.add(property);
	}
	
	@DeleteMapping("/properties/{id}")
	public Object delete(@PathVariable int id) {
		return propertyService.delete(id);
	}
	@PutMapping("/properties")
	public Property update(@RequestBody Property property) {
		return propertyService.add(property);
	}
}
