package com.neighborhood.global.exception.errorCode;

import com.neighborhood.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum LoginErrorCode implements ErrorCode {
    AUTHCODE_EXPIRED(HttpStatus.BAD_REQUEST, "인증번호가 만료되었습니다."),
    AUTHCODE_WRONG(HttpStatus.BAD_REQUEST, "인증번호가 일치하지 않습니다."),
    FAMILY_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 다른 가족에 속해있습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
