package org.springboot.tmall.dao;

import java.util.List;
import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.Property;
import org.springboot.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer>{
	public List<PropertyValue> findByProductOrderByIdDesc(Product product);
	public PropertyValue getByPropertyAndProduct(Property property,Product product);
}

