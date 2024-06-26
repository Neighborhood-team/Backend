package com.neighborhood.domain.login.jwt;

import com.neighborhood.domain.login.dto.LoginResponseDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.service.CustomUserDetailsService;
import com.neighborhood.global.exception.*;
import com.neighborhood.global.exception.errorCode.ErrorCode;
import com.neighborhood.global.exception.errorCode.JwtErrorCode;
import com.neighborhood.global.util.RedisUtil;
import io.jsonwebtoken.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Log4j2
@RequiredArgsConstructor
@Component
public class TokenProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;
    private final CustomUserDetailsService userDetailsService;
    private final RedisUtil redisUtil;
    private static long accessTokenValidTime = 3 * 24 * 60 * 60L; //  3일 : 3 * 24 * 60 * 60L
    private static long refreshTokenValidTime = 2 * 7 * 24 * 60 * 60L; // 2주 : 2 * 7 * 24 * 60 * 60L
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(TokenType tokenType, String memberId) {
        long tokenValidTime;

        if(tokenType == TokenType.AccessToken) {
            tokenValidTime = accessTokenValidTime;
            Claims claims = Jwts.claims().setSubject(memberId);
            claims.put("tokenType", "access");
            Date now = new Date();

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + tokenValidTime * 1000L))
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();

        }
        else {
            tokenValidTime = refreshTokenValidTime;
            Claims claims = Jwts.claims().setSubject(memberId);
            claims.put("tokenType", "refresh");
            Date now = new Date();

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + tokenValidTime * 1000L))
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();
        }
    }

    public String createAccessToken(String memberId) {
        String accessToken = createToken(TokenType.AccessToken, memberId);
        return accessToken;
    }

    public String createRefreshToken(String memberId) {
        String refreshToken = createToken(TokenType.RefreshToken, memberId);
        redisUtil.setDataExpire(memberId, refreshToken, refreshTokenValidTime);
        return refreshToken;
    }

    public Authentication getAuthentication(String token) throws UsernameNotFoundException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getMemberId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getMemberId(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String getTokenType(String token) {
        return String.valueOf(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("tokenType"));
    }

    public String resolveToken(HttpServletRequest request) throws NullTokenException, RequestWithRefreshTokenException {
        String token = request.getHeader("token");
        if (token != null) {
            if(getTokenType(token).equals("refresh")) {
                throw new RequestWithRefreshTokenException();
            }
            return token;
        }
        log.info(request.getRequestURI());
        throw new NullTokenException();
    }

    public void validateToken(String token) throws SignatureException, ExpiredJwtException {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

        if(!claimsJws.getBody().getExpiration().before(new Date())) {
            return;
        }
    }

    private boolean checkRefreshTokenIsExpired(String token) {

        if (!getTokenType(token).equals("refresh")) {
            throw new ReIssueWithAccessTokenException();
        }

        String memberId = getMemberId(token);
        // Refresh token이 만료된 경우 true 반환
        if (!redisUtil.hasData(memberId)) {
            return true;
        }

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claimsJws.getBody().getExpiration().before(new Date());
    }

    public LoginResponseDto reIssue(String refreshToken) {
        String memberId = getMemberId(refreshToken);

//        if (!checkRefreshTokenIsExpired(refreshToken)) {
//            String newAccessToken = createAccessToken(memberId);
//            String newRefreshToken = createRefreshToken(memberId);
//
//            return LoginResponseDto.builder()
//                    .accessToken(newAccessToken)
//                    .refreshToken(newRefreshToken)
//                    .memberId(memberId)
//                    .build();
//            }
//        return null;

        if (checkRefreshTokenIsExpired(refreshToken)) {
            throw new RefreshTokenExpiredException();
        }

        // Refresh Token이 유효하면 Access Token, Refresh Token 재발급
        String newAccessToken = createAccessToken(memberId);
        String newRefreshToken = createRefreshToken(memberId);

        return LoginResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .memberId(memberId)
                .build();
    }

    public void expireToken(String memberId) {
        redisUtil.deleteData(memberId);
    }

}
