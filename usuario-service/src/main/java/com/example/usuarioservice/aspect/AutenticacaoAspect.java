package com.example.usuarioservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AutenticacaoAspect {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoAspect.class);

    @Before("@annotation(com.example.usuarioservice.annotations.LogAutenticacao)")
    public void beforeAutenticacao(JoinPoint joinPoint) {
        logger.info("Iniciando método de autenticação: {}", joinPoint.getSignature().getName());
    }

    @After("@annotation(com.example.usuarioservice.annotations.LogAutenticacao)")
    public void afterAutenticacao(JoinPoint joinPoint) {
        logger.info("Finalizou método de autenticação: {}", joinPoint.getSignature().getName());
    }
}
