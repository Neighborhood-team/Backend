package com.neighborhood.domain.login.service;

import com.neighborhood.domain.login.dto.LoginResponseDto;
import com.neighborhood.domain.login.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final TokenProvider tokenProvider;

    @Transactional
    public LoginResponseDto login(String memberId) {


        return LoginResponseDto.builder()
                .accessToken(tokenProvider.createAccessToken(memberId))
                .refreshToken(tokenProvider.createRefreshToken(memberId))
                .memberId(memberId)
                .build();
    }

    @Transactional
    public void logout(String accessToken) {
        String memberId = tokenProvider.getMemberId(accessToken);
        tokenProvider.expireToken(memberId);
    }

    @Transactional
    public LoginResponseDto reIssue(String token) {
        return tokenProvider.reIssue(token);
    }

}
