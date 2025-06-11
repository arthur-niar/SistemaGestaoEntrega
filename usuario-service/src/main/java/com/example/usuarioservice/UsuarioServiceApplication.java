package com.example.usuarioservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class UsuarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsuarioServiceApplication.class, args);
    }
}
