package com.neighborhood.global.exception;


import com.neighborhood.domain.login.dto.ErrorJson;
import com.neighborhood.global.exception.errorCode.JwtErrorCode;
import com.neighborhood.global.util.BaseController;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Log4j2
public class AuthExceptionHandler extends BaseController {

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<?> handleRefreshTokenExpiredException(RefreshTokenExpiredException e) {
        log.warn("RefreshTokenExpiredException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.REFRESH_TOKEN_EXPIRED);
        return sendResponse(errorJson, HttpStatus.valueOf(402));
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleTokenExpiredException(ExpiredJwtException e) {
        log.warn("TokenExpiredException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.TOKEN_EXPIRED);
        return sendResponse(errorJson, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullTokenException.class)
    public ResponseEntity<?> handleNullTokenException(NullTokenException e) {
        log.warn("NullTokenException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.ACCESS_TOKEN_NULL);
        return sendResponse(errorJson, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReIssueWithAccessTokenException.class)
    public ResponseEntity<?> handleNullTokenException(ReIssueWithAccessTokenException e) {
        log.warn("ReIssueWithAccessTokenException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.NOT_REFRESH_TOKEN);
        return sendResponse(errorJson, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequestWithRefreshTokenException.class)
    public ResponseEntity<?> handleRequestWithRefreshTokenException(RequestWithRefreshTokenException e) {
        log.warn("RequestWithRefreshTokenException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.NOT_ACCESS_TOKEN);
        return sendResponse(errorJson, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<?> handleSignatureException(SignatureException e) {
        log.warn("SignatureException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.USED_BAD_TOKEN);
        return sendResponse(errorJson, HttpStatus.valueOf(402));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<?> handleMalformedJwtException(MalformedJwtException e) {
        log.warn("MalformedJwtException");
        ErrorJson errorJson = new ErrorJson(e, JwtErrorCode.USED_BAD_TOKEN);
        return sendResponse(errorJson, HttpStatus.valueOf(402));
    }
}