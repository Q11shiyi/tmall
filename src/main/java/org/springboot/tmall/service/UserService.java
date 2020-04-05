package org.springboot.tmall.service;


import org.springboot.tmall.dao.UserDao;
import org.springboot.tmall.pojo.User;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired UserDao userDao;
	public Page4Navigator<User> list(int start,int size,int navigatePages){
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Page<User> page=userDao.findAll(pageable);
		return new Page4Navigator<User>(page, navigatePages);
	}
}
