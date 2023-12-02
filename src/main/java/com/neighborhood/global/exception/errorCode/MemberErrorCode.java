package com.neighborhood.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {
    DAD_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "가족 내에 아빠가 이미 존재합니다."),
    MOM_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "가족 내에 엄마가 이미 존재합니다."),
    FAMILY_NOT_FOUND(HttpStatus.NOT_FOUND, "가족이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 전화번호로 가입한 사용자가 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
