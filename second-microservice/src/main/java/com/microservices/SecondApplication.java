package com.microservices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@EnableFeignClients
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
@RestController
public class SecondApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondApplication.class, args);
    }

    @Bean
    FirstClient.FirstClientFallback firstClientFallback() {
        return new FirstClient.FirstClientFallback();
    }

    @Autowired
    private FirstClient firstClient;

    @RequestMapping(value = "/random", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SecondResponse getRandomUUID() {
        return new SecondResponse(firstClient.getRandomUUID(), UUID.randomUUID().toString());
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class SecondResponse {
        String firstRandomUUID;
        String secondRandomUUID;
    }
}
