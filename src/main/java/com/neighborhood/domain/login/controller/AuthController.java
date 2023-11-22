package com.neighborhood.domain.login.controller;

import com.neighborhood.domain.login.dto.LoginRequestDto;
import com.neighborhood.domain.login.dto.LoginResponseDto;
import com.neighborhood.domain.login.service.LoginSMSService;
import com.neighborhood.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private final LoginSMSService loginSMSService;
    private final LoginService loginService;

    @PostMapping("/sms/send/{phone}")
    public SingleMessageSentResponse sendSMS(@PathVariable String phone) {
        SingleMessageSentResponse response = loginSMSService.sendSMS(phone);
        return response;
    }

    @GetMapping("/login/verify")
    public LoginResponseDto verifySMS(@RequestBody LoginRequestDto requestDto) {
        LoginResponseDto responseDto = loginSMSService.verifySMS(requestDto);

        return responseDto;
    }

    @PostMapping("/reIssue")
    public LoginResponseDto reIssue(@RequestHeader("token") String token)  {
        LoginResponseDto responseDto = loginService.reIssue(token);

        return responseDto;
    }
}