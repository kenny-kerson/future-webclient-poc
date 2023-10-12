package com.kenny.futurewebclientpoc.service;

import com.kenny.futurewebclientpoc.controller.dto.FutureDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@Slf4j
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public CompletableFuture<FutureDTO.Out> api(final String startDateTime) {
        log.warn("# WebClientService external api call!!");

        final CompletableFuture<FutureDTO.Out> apiOutput = webClient.get()
                .uri("/external/mock_api")
                .retrieve()
                .bodyToMono(String.class)
                .map(output -> FutureDTO.Out.builder()
                        .endDateTime(output)
                        .build())
                .toFuture();

        log.warn("# api call end & return ");
        return apiOutput;
    }
}