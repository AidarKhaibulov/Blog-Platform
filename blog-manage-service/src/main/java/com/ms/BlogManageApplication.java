package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class BlogManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogManageApplication.class, args);
    }

}
