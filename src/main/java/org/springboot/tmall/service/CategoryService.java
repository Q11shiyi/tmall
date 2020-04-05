package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.CategoryDao;
import org.springboot.tmall.pojo.Category;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	@Autowired CategoryDao categoryDao;
	
	public List<Category> list(){
		Sort sort=new Sort(Sort.Direction.DESC, "id");
		return categoryDao.findAll(sort);//JPA已经写好的findAll方法,使用Sort对id进行排序
	}
	
	//改写一个list方法,加上三个参数,返回值为自定义的Page分页的封装类,方便页面解析获取数据
	public Page4Navigator<Category> list(int start,int size,int navigatePages){
		Sort sort=new Sort(Sort.Direction.DESC, "id");//定义排序规则
		//使用该Pageable对象带三个参进行分页查询
		//PageRequest.of:三个参数 1.page :要查询的当前页码 从零开始,即显示第五页传入四;2.size 页面大小;3.sort 排序规则
		Pageable pageable=PageRequest.of(start, size, sort);//new PageRequest(start, size,sort);该构造已弃用,改为静态方法of
		Page<Category> PageFromJPA=categoryDao.findAll(pageable);//通过该pageable带参进行分页查询,并返回一个page类用来封装所有信息
		return new Page4Navigator<>(PageFromJPA,navigatePages);//将该Page再次封装进我们自定义的类中以便在页面的更好显示
	}
	
	public void add(Category category) {
		categoryDao.save(category);
	}
	public void delete(int id) {
		categoryDao.deleteById(id);
	}
	public Category get(int id) {
		return categoryDao.getOne(id);
	}
	public Category update(Category category) {
		return categoryDao.save(category);
	}
}
