package com.transactions.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigProperties {
    @Value("${userId}")
    private int userId;
    
    public int getUserId() {
        return userId;
    }



}
