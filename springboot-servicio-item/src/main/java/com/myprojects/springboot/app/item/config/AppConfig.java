package com.myprojects.springboot.app.item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean(name = "clientRest")
    public RestTemplate registerRestTemplate() {
        return new RestTemplate();
    }
}
