package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.PropertyDao;
import org.springboot.tmall.pojo.Category;
import org.springboot.tmall.pojo.Property;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PropertyService {
	@Autowired CategoryService categoryService;
	@Autowired PropertyDao propertyDao;
	public Property get(int id) {
		return propertyDao.getOne(id);
	}
	
	public Page4Navigator<Property> list(int cid,int start,int size,int navigatePages){
		Category category=categoryService.get(cid);
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size,sort);
		Page<Property> pageFromJPA=propertyDao.findByCategory(category, pageable);
		Page4Navigator<Property> page=new Page4Navigator<>(pageFromJPA, navigatePages);
		return page;
	}
	public List<Property> list(Category category){
		return propertyDao.findByCategory(category);
	}
	public Property add(Property property) {
		return propertyDao.save(property);
	}
	
	public Property update(Property property) {
		return propertyDao.save(property);
	}
	public Object delete(int id) {
		propertyDao.deleteById(id);
		return null;
	}
}
