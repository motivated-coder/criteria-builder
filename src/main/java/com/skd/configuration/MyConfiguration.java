package com.skd.configuration;

import com.skd.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class MyConfiguration {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Bean
//    public OrderService someService() {
//        return new OrderService(entityManager);
//    }
}
