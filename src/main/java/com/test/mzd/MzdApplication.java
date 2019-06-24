package com.test.mzd;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@MapperScan(basePackages = "com.test.mzd.dao")
@ImportResource(locations={"classpath:doubbo-customer.xml"})
public class MzdApplication {

    public static void main(String[] args) {
        SpringApplication.run(MzdApplication.class, args);
    }
}
