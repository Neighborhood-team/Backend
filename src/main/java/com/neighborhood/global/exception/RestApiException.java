package com.neighborhood.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.neighborhood.global.exception.ErrorCode;

/**
 * 발생한 예외를 처리해줄 예외 클래스.
 * 일반적인 비즈니스 로직은 따로 catch해서 처리할 것이 없으므로 언체크 예외(RuntimeException) 상속
 */
@Getter
@RequiredArgsConstructor
public class RestApiException extends RuntimeException {

    private final ErrorCode errorCode;
}
