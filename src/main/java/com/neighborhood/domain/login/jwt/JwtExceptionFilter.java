package com.neighborhood.domain.login.jwt;

import com.neighborhood.domain.login.dto.ErrorJson;
import com.neighborhood.global.exception.NullTokenException;
import com.neighborhood.global.exception.ReIssueWithAccessTokenException;
import com.neighborhood.global.exception.RefreshTokenExpiredException;
import com.neighborhood.global.exception.RequestWithRefreshTokenException;
import com.neighborhood.global.exception.errorCode.ErrorCode;
import com.neighborhood.global.exception.errorCode.JwtErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RefreshTokenExpiredException e) {
            setErrorResponse(response, e, JwtErrorCode.REFRESH_TOKEN_EXPIRED);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, e, JwtErrorCode.TOKEN_EXPIRED);
        } catch (SignatureException e) {
            setErrorResponse(response, e, JwtErrorCode.USED_BAD_TOKEN);
        } catch (MalformedJwtException e) {
            setErrorResponse(response, e, JwtErrorCode.USED_BAD_TOKEN);
        } catch (UsernameNotFoundException e) {
            setErrorResponse(response, e, JwtErrorCode.TOKEN_IS_NOT_YOURS);
        } catch (NullTokenException e) {
            setErrorResponse(response, e, JwtErrorCode.ACCESS_TOKEN_NULL);
        } catch (ReIssueWithAccessTokenException e) {
            setErrorResponse(response, e, JwtErrorCode.NOT_REFRESH_TOKEN);
        } catch (RequestWithRefreshTokenException e) {
            setErrorResponse(response, e, JwtErrorCode.NOT_ACCESS_TOKEN);
        }
    }

    public void setErrorResponse(HttpServletResponse response, Exception e, JwtErrorCode errorCode) {

        ErrorJson errorJson = new ErrorJson(e, errorCode);
        response.setStatus(errorCode.getHttpStatus());
        response.setContentType("application/json; charset=UTF-8");

        try {
            String json = errorJson.convertToJson();
            response.getWriter().write(json);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        log.warn("*** 에러 발생! Message : {}, ErrorCode : {} ***", errorCode.getMessage(), errorCode.getHttpStatus());
    }
}
