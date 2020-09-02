package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.sj.repository")
public class UserinfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserinfoApplication.class,args);
    }
}
