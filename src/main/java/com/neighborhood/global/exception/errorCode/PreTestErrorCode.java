package com.neighborhood.global.exception.errorCode;


import com.neighborhood.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 특정 도메인에 대해 구체적으로 내려가는 예외들은 다음과 같이 구현 해야함
 */
@Getter
@RequiredArgsConstructor
public enum PreTestErrorCode implements ErrorCode {

    // TODO: @nacho Kang이 필요한 부분 추가
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않은 ~"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
