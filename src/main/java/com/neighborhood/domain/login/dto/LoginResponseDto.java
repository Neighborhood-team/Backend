package com.neighborhood.domain.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@Data
public class LoginResponseDto {
    @Schema(description = "Access 토큰")
    private String accessToken;
    @Schema(description = "Refresh 토큰")
    private String refreshToken;
    @Schema(description = "토큰을 가지고 있는 사용자의 id", example = "1")
    private String memberId;
    @Schema(description = "신규 사용자면 true, 기존 사용자면 false", example = "true")
    private Boolean isNew;
}
