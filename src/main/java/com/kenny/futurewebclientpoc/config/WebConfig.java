package com.kenny.futurewebclientpoc.config;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.HttpResources;
import reactor.netty.resources.ConnectionProvider;

@Configuration
@Slf4j
public class WebConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        log.warn("# WebClient.Builder Bean Creation");

        return WebClient.builder();
    }

//    @Bean
//    public WebClient webClient(final MeterRegistry meterRegistry) {
//        final NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//
//        // 쓰레드풀 모니터링
//        Gauge.builder("netty.eventLoopGroup.activeThreads", eventLoopGroup, EventLoopGroup::activeCount)
//                .register(meterRegistry);
//
//        new ReactorClientHttpConnector(HttpResources.set(ConnectionProvider.newConnection()).get().addGroup(eventLoopGroup));
//    }
}
