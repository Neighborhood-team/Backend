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
    public LoginResponseDto login(String memberId, Boolean isNew) {


        return LoginResponseDto.builder()
                .accessToken(tokenProvider.createAccessToken(memberId))
                .refreshToken(tokenProvider.createRefreshToken(memberId))
                .memberId(memberId)
                .isNew(isNew)
                .build();
    }

    @Transactional
    public void logout(String token) {
        String logoutMemberId = tokenProvider.getMemberId(token);
        tokenProvider.expireToken(logoutMemberId);
    }

    @Transactional
    public LoginResponseDto reIssue(String token) {
        return tokenProvider.reIssue(token);
    }

}
