package com.neighborhood.domain.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class LoginRequestDto {
    @Schema(description = "SMS 인증번호를 받은 전화번호", example = "01012341234")
    private String phone;
    @Schema(description = "수신한 SMS 인증번호", example = "123456")
    private String smsAuthCode;
}
