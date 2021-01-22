package com.ethan.mybatismultidatasourcedemo;

import com.ethan.mybatismultidatasourcedemo.test1.service.Test1MoneyService;
import com.ethan.mybatismultidatasourcedemo.test2.service.Test2MoneyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisMultiDatasourceDemoApplication {

    public MybatisMultiDatasourceDemoApplication(Test1MoneyService test1Service, Test2MoneyService test2Service) {
        test1Service.queryList();
        test2Service.queryList();
    }

    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiDatasourceDemoApplication.class, args);
    }

}
