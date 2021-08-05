package com.work.note.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName EurekaApplicationRun
 * @Description TODO
 * @Author LiuZhao
 * @Date 2021/8/5 9:56
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplicationRun {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplicationRun.class, args);
    }
}
