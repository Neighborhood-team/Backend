package com.neighborhood.domain.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String memberId;
}
