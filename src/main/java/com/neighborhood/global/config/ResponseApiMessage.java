package com.neighborhood.global.config;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseApiMessage {
    int httpStatus;
    String message;
    Object responseData;

    @Builder
    public ResponseApiMessage(int httpStatus, String message, Object responseData){
        this.httpStatus = httpStatus;
        this.message = message;
        this.responseData = responseData;
    }
}
