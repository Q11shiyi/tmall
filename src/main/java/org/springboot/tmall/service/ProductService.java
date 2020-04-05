package org.springboot.tmall.service;

import org.springboot.tmall.dao.ProductDao;
import org.springboot.tmall.pojo.Category;
import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired CategoryService  categoryService;
	@Autowired ProductDao productDao;
	@Autowired ProductImageService productImageService;
	public Product get(int id) {
		return productDao.getOne(id);
	}
	public Page4Navigator<Product> list(int cid,int start,int size,int navigatePages){
		Category category=categoryService.get(cid);
		Sort sort=new Sort(Sort.Direction.DESC,"id");
		Pageable pageable=PageRequest.of(start, size, sort);
		Page<Product> page =productDao.findByCategory(category, pageable);
		productImageService.setFirstProductImages(page.getContent());
		return new Page4Navigator<Product>(page, navigatePages);
	}
	
	public Product add(Product product) {
		return productDao.save(product);
	}
	public Product update(Product product) {
		return productDao.save(product);
	}
	public String delete(int id) {
		productDao.deleteById(id);
		return null;
	}
}
