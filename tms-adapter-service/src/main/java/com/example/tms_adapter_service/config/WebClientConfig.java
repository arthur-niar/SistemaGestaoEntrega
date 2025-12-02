package com.example.tms_adapter_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class WebClientConfig {

    @Bean
    @Qualifier("pedidoClient")
    public WebClient pedidoClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082") // URL do pedido-service ↑
                .build();
    }

    @Bean
    @Qualifier("tmsClient")
    public WebClient tmsClient() {
        return WebClient.builder()
                .baseUrl("http://tms-external-api.com") // mock por enquanto ↑
                .build();
    }
}
