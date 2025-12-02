package com.example.tms_adapter_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TmsAdapterServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmsAdapterServiceApplication.class, args);
    }
}
