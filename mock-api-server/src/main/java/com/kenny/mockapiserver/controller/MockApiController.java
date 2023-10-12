package com.kenny.mockapiserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
public class MockApiController {
    @GetMapping("/external/mock_api")
    public String getTimeStamp() {
        log.warn("# ExternalMockController getTimeStamp() START!!");

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.warn("# External Mock API Timestamp : {}", LocalDateTime.now());
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
