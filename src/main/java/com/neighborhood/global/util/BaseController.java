package com.neighborhood.global.util;

import com.neighborhood.global.config.ResponseApiMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class BaseController {

    /**
     * JSON 형식으로 HTTP 응답을 생성하여 반환하는 메소드
     *
     * @param httpStatus   HTTP 상태 코드
     * @param message      응답 메시지
     * @param responseData 응답 데이터
     * @return ResponseEntity 객체, JSON 응답, HTTP 상태 코드
     */

    public ResponseEntity<ResponseApiMessage> sendResponseHttpByJson(int httpStatus, String message, Object responseData) {
        ResponseApiMessage responseApiMessage = ResponseApiMessage.builder()
                .httpStatus(httpStatus)
                .message(message)
                .responseData(responseData)
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(responseApiMessage, headers, 200);
    }

    public ResponseEntity<?> sendResponse(Object data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<Object>(data, headers, HttpStatus.OK);
    }

    public ResponseEntity<?> sendResponse(Object data, HttpStatus httpStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity<Object>(data, headers, httpStatus);
    }

    public ResponseEntity<?> sendResponse() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return new ResponseEntity(headers, HttpStatus.OK);
    }
}
