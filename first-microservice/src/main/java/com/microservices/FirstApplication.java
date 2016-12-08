package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
@RestController
public class FirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public String getRandomUUID() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(500);
        return UUID.randomUUID().toString();
    }
}
