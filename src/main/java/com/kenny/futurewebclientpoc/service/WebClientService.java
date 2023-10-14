package com.kenny.futurewebclientpoc.service;

import com.kenny.futurewebclientpoc.controller.dto.FutureDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(final WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8081")
                .build();
    }

    public CompletableFuture<FutureDTO.Out> nonblockingApi(final String startDateTime) {
        log.warn("# WebClientService external nonblockingApi call!!");

        final CompletableFuture<FutureDTO.Out> apiOutput = webClient.get()
                .uri("/external/mock_api")
                .retrieve()
                .bodyToMono(String.class)
                .map(output -> FutureDTO.Out.builder()
                        .endDateTime(output)
                        .build())
                .toFuture()
                .thenApply(result -> {
                        log.warn("# result : {}", result);
                        return result;
                })
                .orTimeout(20L, TimeUnit.SECONDS)
                .exceptionally(e -> {
                        log.warn("# CompletableFuture error : ", e);
                        return FutureDTO.Out.builder()
                                .endDateTime("error")
                                .build();
                })
        ;

        log.warn("# api call end & return ");
        return apiOutput;
    }

    public FutureDTO.Out blockingApi(final String startDateTime) {
        log.warn("# WebClientService external blockingApi call!!");

        final FutureDTO.Out apiOutput = webClient.get()
                .uri("/external/mock_api")
                .retrieve()
                .bodyToMono(String.class)
                .map(output -> FutureDTO.Out.builder()
                        .endDateTime(output)
                        .build())
                .block();

        log.warn("# api call end & return ");
        return apiOutput;
    }
}