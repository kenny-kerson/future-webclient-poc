package com.kenny.mockapiserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@RestController
@Slf4j
public class MockApiController {
    @GetMapping("/external/mock_api")
    public Long getTimeStamp() {
        log.warn("# ExternalMockController getTimeStamp() START!!");

        final long second = new Random().nextInt() % 10L;

        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.warn("# External Mock API second : {}", second);
        return second;
    }
}
