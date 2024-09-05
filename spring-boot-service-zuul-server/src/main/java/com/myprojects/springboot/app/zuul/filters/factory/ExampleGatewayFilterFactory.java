package com.myprojects.springboot.app.zuul.filters.factory;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.Configuration> {

    private final Logger logger = LoggerFactory.getLogger(ExampleGatewayFilterFactory.class);

    public ExampleGatewayFilterFactory() {
        super(Configuration.class);
    }

    @Override
    public GatewayFilter apply(Configuration config) {
        return (exchange, chain) -> {
            logger.info("Executing pre gateway filter factory {}", config.message);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.cookieValue).ifPresent((cooke -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cooke).build());
                }));
                logger.info("Executing post gateway filter factory {}", config.message);
            }));
        };
    }

    @Getter
    @Setter
    public static class Configuration {
        private String message;
        private String cookieValue;
        private String cookieName;
    }
}
