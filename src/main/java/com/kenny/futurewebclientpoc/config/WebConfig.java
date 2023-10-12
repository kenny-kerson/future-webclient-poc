package com.kenny.futurewebclientpoc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        log.warn("# WebClient.Builder Bean Creation");

        return WebClient.builder();
    }
}
