package org.springboot.tmall.dao;

import java.util.List;

import org.springboot.tmall.pojo.Category;
import org.springboot.tmall.pojo.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDao extends JpaRepository<Property, Integer> {
	Page<Property> findByCategory(Category category,Pageable pageable);
	List<Property> findByCategory(Category category);
}
