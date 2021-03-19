package com.how2java.tmall;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.how2java.tmall.util.PortUtil;
/**
 * @author huanghuiqiang
 */
@SpringBootApplication
@EnableCaching
@EnableElasticsearchRepositories(basePackages = "com.how2java.tmall.es")
@EnableJpaRepositories(basePackages = {"com.how2java.tmall.dao", "com.how2java.tmall.pojo"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
