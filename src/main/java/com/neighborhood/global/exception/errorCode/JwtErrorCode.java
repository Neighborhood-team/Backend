package com.neighborhood.global.exception.errorCode;

import com.neighborhood.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {
    ACCESS_TOKEN_NULL(HttpStatus.BAD_REQUEST, "Access Token이 Header에 없습니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "Refresh Token이 만료되었습니다. 재로그인 해주세요."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "Access Token이 만료되었습니다. Refresh Token으로 요청해주세요.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
