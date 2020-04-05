package com.how2java.tmall.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.how2java.tmall.pojo.Product;

/**
 *  es全文检索jpa整合方法,使用了低版本的es
 */
public interface ProductESDAO extends ElasticsearchRepository<Product,Integer>{

}