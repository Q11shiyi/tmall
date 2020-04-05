package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.ProductImageDao;
import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageService {
	@Autowired ProductService productService;
	@Autowired ProductImageDao productImageDao;
	public static final String type_single="single";
	public static final String type_detail="detail";
			
	public ProductImage get(int id) {
		return productImageDao.getOne(id);
	}
	public List<ProductImage> listSingleProductImages(Product product){
		return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_single);
	}
	public List<ProductImage> listDetailProductImages(Product product){
		return productImageDao.findByProductAndTypeOrderByIdDesc(product, type_detail);
	}
	
	public ProductImage add(ProductImage productImage) {
		return productImageDao.save(productImage);
	}
	public String delete(int id) {
		productImageDao.deleteById(id);
		return null;
	}
	
	public void setFirstProductImage(Product product) {
		List<ProductImage> pi=listSingleProductImages(product);
		if(!pi.isEmpty())
			product.setFirstProductImage(pi.get(0));
		else
			product.setFirstProductImage(new ProductImage());;
	}
	public void setFirstProductImages(List<Product> products) {
		for(Product p:products ) {
			setFirstProductImage(p);
		}
	}
}
