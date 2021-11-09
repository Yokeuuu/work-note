package com.work.note;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName TestApplication
 * @Description TODO
 * @Author LiuZhao
 * @Date 2021/7/27 17:51
 * @Version 1.0
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class TestApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(TestApplicationRun.class,args);
    }
}
