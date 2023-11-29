package com.group.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.group.study.mapper")
public class GroupStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupStudyApplication.class, args);
    }

}
