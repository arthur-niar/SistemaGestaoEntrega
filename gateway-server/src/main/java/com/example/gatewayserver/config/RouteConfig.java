package com.example.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("usuario-service", r -> r
                        .path("/api/usuarios/**")
                        .uri("http://localhost:8081"))
                .route("cliente-service", r -> r
                        .path("/api/clientes/**")
                        .uri("http://localhost:8084"))
                .route("entregador-service", r -> r
                        .path("/api/entregadores/**")
                        .uri("http://localhost:8083"))
                .route("pedido-service", r -> r
                        .path("/api/pedidos/**")
                        .uri("http://localhost:8082"))
                .route("tms-adapter-service", r -> r
                        .path("/tms/**")
                        .uri("http://localhost:8085"))
                .route("tms-fake-service", r -> r
                        .path("/shipments/**")
                        .uri("http://localhost:8086"))
                .build();
    }
}
