package org.springboot.tmall.controller;

import org.springboot.tmall.pojo.User;
import org.springboot.tmall.service.UserService;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired UserService userService;
	
	@GetMapping("users")
	public Page4Navigator<User> list(@RequestParam(value = "start",defaultValue = "0") int start,
@RequestParam(value = "size",defaultValue = "5") int size){
		start=start<0?0:start;
		return userService.list(start, size, 5);
	}
}
