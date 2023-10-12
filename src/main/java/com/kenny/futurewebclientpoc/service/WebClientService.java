package com.kenny.futurewebclientpoc.service;

import com.kenny.futurewebclientpoc.controller.dto.FutureDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebClientService {
    public CompletableFuture<FutureDTO.Out> api(final String startDateTime) {
        return null;
    }
}i