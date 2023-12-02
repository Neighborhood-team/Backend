package com.neighborhood.domain.login.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighborhood.global.exception.errorCode.ErrorCode;
import com.neighborhood.global.exception.errorCode.JwtErrorCode;
import lombok.Getter;

@Getter
public class ErrorJson {
    public String message;
    public int errorCode;

    public String convertToJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public ErrorJson(Exception e, JwtErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.errorCode = errorCode.getHttpStatus();
    }
}
