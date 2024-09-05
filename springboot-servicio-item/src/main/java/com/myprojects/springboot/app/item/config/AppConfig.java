package com.myprojects.springboot.app.item.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@EnableConfigurationProperties
@Configuration
public class AppConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustom() {
        return factory -> factory.configureDefault(
            id -> {
                return new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.custom()
                                .slidingWindowSize(10)
                                .failureRateThreshold(50)
                                .waitDurationInOpenState(Duration.ofSeconds(10L))
                                .permittedNumberOfCallsInHalfOpenState(5)
                                .slowCallDurationThreshold(Duration.ofSeconds(2L))
                                .build())
                        .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build())
                        .build();
            }
        );
    }
}
