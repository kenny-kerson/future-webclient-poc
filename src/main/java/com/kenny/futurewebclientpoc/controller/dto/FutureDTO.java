package com.kenny.futurewebclientpoc.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class FutureDTO {

    @Getter
    @Builder
    @ToString
    public static class Out {
        private final String second;
    }
}
