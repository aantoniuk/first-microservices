package com.microservices;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient(value = "first-microservice", fallback = FirstClient.FirstClientFallback.class)
public interface FirstClient {

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    String getRandomUUID();

    class FirstClientFallback implements FirstClient {

        private static final String RANDOM_UUID = "-1";

        @Override
        public String getRandomUUID() {
            return UUID.fromString(RANDOM_UUID).toString();
        }
    }

}
