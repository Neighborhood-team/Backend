package com.neighborhood.domain.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LoginRequestDto {
    private String phone;
    private String smsAuthCode;
}
