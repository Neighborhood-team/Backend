package com.neighborhood.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode {
    ACCESS_TOKEN_NULL(400, "Access Token이 Header에 없습니다."),
    REFRESH_TOKEN_EXPIRED(402, "Access token이 유효한데 ReIssue를 요청하셨습니다."),
    TOKEN_EXPIRED(401, "Token이 만료되었습니다. Refresh Token으로 reIssue 요청해주세요. reIssue 요청시 해당 오류가 발생할 경우 재로그인 해주세요."),
    USED_BAD_TOKEN(402, "지원하지 않는 형식의 Token을 사용하였습니다."),
    TOKEN_IS_NOT_YOURS(400, "해당 사용자의 Access Token이 아닙니다."),
    NOT_REFRESH_TOKEN(400, "Access token으로 reIssue를 요청하셨습니다. ReIssue는 Refresh token으로만 가능합니다."),
    NOT_ACCESS_TOKEN(400, "Refresh token으로 요청하셨습니다. Access token으로 요청해주세요.")
    ;

    private final int httpStatus;
    private final String message;
}
