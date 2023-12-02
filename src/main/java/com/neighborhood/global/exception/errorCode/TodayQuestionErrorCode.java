package com.neighborhood.global.exception.errorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TodayQuestionErrorCode implements ErrorCode {

    DUPLICATED_ANSWER_REQUEST(HttpStatus.BAD_REQUEST, "이미 오늘자 질문에 대답을 했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
