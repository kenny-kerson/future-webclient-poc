package com.kenny.futurewebclientpoc.service;

import com.kenny.futurewebclientpoc.controller.dto.FutureDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8080")
                .build();
    }

    public CompletableFuture<FutureDTO.Out> api(final String startDateTime) {
        log.warn("# WebClientService external api call!!");

        return webClient.get()
                .uri("/external/mock_api")
                .retrieve()
                .bodyToMono(FutureDTO.Out.class)
                .toFuture()
        ;
}