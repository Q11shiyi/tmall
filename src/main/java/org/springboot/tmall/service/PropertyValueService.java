package org.springboot.tmall.service;

import java.util.List;

import org.springboot.tmall.dao.PropertyValueDao;
import org.springboot.tmall.pojo.Product;
import org.springboot.tmall.pojo.Property;
import org.springboot.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PropertyValueService {
	@Autowired PropertyValueDao propertyValueDao;
	@Autowired PropertyService propertyService;
	//对于属性值来说,当产品和产品对应的属性确定后,属性值就是一个字符串必须确定,可以初始化为空
	//属性值只有修改功能,不可添加,创建产品后没有属性值,此时访问editPropertyValue页面时无属性值
	//所以在访问该页面时,初始化属性值到数据库中
	public void init(Product product) {
		List<Property> propertys=propertyService.list(product.getCategory());
		for(Property property:propertys) {
			PropertyValue propertyValue=getByPropertyAndProduct(property,product);
			if(propertyValue==null) {
				propertyValue=new PropertyValue();
				propertyValue.setProduct(product);
				propertyValue.setProperty(property);
				propertyValueDao.save(propertyValue);
			}
		}
	}
	//一个产品的一个属性下只有一个属性值,获取该属性值
	public PropertyValue getByPropertyAndProduct(Property property,Product product){
		return propertyValueDao.getByPropertyAndProduct(property, product);
	}
	//查询产品对应的所有属性值,为页面显示
	public List<PropertyValue> findByProduct(Product product){
		List<PropertyValue> pvs=propertyValueDao.findByProductOrderByIdDesc(product);
		return pvs;
	}
	public PropertyValue update(PropertyValue propertyValue) {
		return propertyValueDao.save(propertyValue);
	}
}
