package com.zoey.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@MapperScan("com.zoey.blog.dao")
@SpringBootApplication
public class ZoeyBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZoeyBlogApplication.class, args);
    }

}
