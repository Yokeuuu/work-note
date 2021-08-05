package com.work.note.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName ConfigApplicationRun
 * @Description TODO
 * @Author LiuZhao
 * @Date 2021/8/5 10:02
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class ConfigApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplicationRun.class,args);
    }
}
