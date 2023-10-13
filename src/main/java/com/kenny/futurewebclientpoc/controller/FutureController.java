package com.kenny.futurewebclientpoc.controller;

import com.kenny.futurewebclientpoc.controller.dto.FutureDTO;
import com.kenny.futurewebclientpoc.service.WebClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FutureController {

    private final WebClientService webClientService;

    @GetMapping("/sync_nonblocking/{startDateTime}")
    public CompletableFuture<FutureDTO.Out> getExternalApiResponse( @PathVariable final String startDateTime ) {
        log.warn("# FutureController getExternalApiResponse() START!!");

        return webClientService.nonblockingApi(startDateTime);
    }

    @GetMapping("/sync_blocking/{startDateTime}")
    public FutureDTO.Out getExternalApiResponseBlocking( @PathVariable final String startDateTime ) {
        log.warn("# FutureController getExternalApiResponseBlocking() START!!");

        return webClientService.blockingApi(startDateTime);
    }
}
