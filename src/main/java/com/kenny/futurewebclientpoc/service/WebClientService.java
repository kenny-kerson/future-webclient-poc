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
                .bodyToMono(Long.class)
                .map(output -> FutureDTO.Out.builder()
                        .second(String.valueOf(output))
                        .build())
                .toFuture()
                .thenApply(result -> {
                        // Future의 결과를 받아 새로운 결과를 생성한다.
                        log.warn("# result : {}", result);
                        return result;
                })
                // 이 시간동안 Future가 완료되지 않으면 익셉션을 발생시킨다.
                .orTimeout(20L, TimeUnit.SECONDS)
                .exceptionally(e -> {
                        // Future에서 익셉션이 발생했을때 동작할 Callback을 정의한다. 즉, 오류가 발생했을때 동작하는 로직을 작성한다.
                        log.warn("# CompletableFuture error : ", e);
                        return FutureDTO.Out.builder()
                                .second("error")
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
                .bodyToMono(Long.class)
                .map(output -> FutureDTO.Out.builder()
                        .second(String.valueOf(output))
                        .build())
                .block();

        log.warn("# api call end & return ");
        return apiOutput;
    }
}