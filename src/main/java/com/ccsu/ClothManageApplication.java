package com.ccsu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class ClothManageApplication {
    public static void main(String[] args) {
        System.setProperty("druid.mysql.usePingMethod","false");
        SpringApplication.run(ClothManageApplication.class,args);
        log.info("项目启动成功....");
    }
}
