package com.neighborhood.global.exception.errorCode;

import org.springframework.http.HttpStatus;

/**
 * 클라이언트에게 보내줄 에러 코드 정의. 여기서는 에러 이름, http 상태코드, 메세지를 가질 수 있다.
 * 해당 인터페이스를 구현하여 각 에러, 예외를 커스텀하게 구현해야한다.
 */
public interface ErrorCode {

    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}
