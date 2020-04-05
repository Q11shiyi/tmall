package org.springboot.tmall.dao;

import java.util.List;

import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageDao extends JpaRepository<ProductImage, Integer> {
	List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product,String type);
}
