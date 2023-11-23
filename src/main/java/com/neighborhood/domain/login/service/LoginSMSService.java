package com.neighborhood.domain.login.service;

import com.neighborhood.domain.login.dto.LoginRequestDto;
import com.neighborhood.domain.login.dto.LoginResponseDto;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.member.service.MemberManageService;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.LoginErrorCode;
import com.neighborhood.global.exception.errorCode.MemberErrorCode;
import com.neighborhood.global.util.RandomCodeUtil;
import com.neighborhood.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Log4j2
public class LoginSMSService {
    //@Value("${spring.jwt.secret}")
    @Value("${coolsms.api.key}")
    private String coolSMSKey;

    @Value("${coolsms.api.secret}")
    private String coolSMSSecret;

    @Value("${coolsms.api.domain}")
    private String coolSMSDomain;

    private final RedisUtil redisUtil;
    private final LoginService loginService;
    private final MemberRepository memberRepository;
    private final MemberManageService memberManageService;

    public SingleMessageSentResponse sendSMS(String phone) {
        Long smsAuthCodeExpireTime = 300L; // 인증번호 유효시간 5분
        DefaultMessageService messageService = NurigoApp.INSTANCE.initialize(coolSMSKey, coolSMSSecret, coolSMSDomain);
        Message message = new Message();
        String smsAuthCode = RandomCodeUtil.generateAuthCode(6);
        // 발신번호 및 수신번호(phone)는 반드시 01012345678 형태로 입력되어야 함
        message.setFrom("01020800732");
        message.setTo(phone);
        message.setText("[동네사람들] 인증번호는 [" + smsAuthCode + "] 입니다.");

        SingleMessageSentResponse response = messageService.sendOne(new SingleMessageSendingRequest(message));
        System.out.println(response);

        redisUtil.setDataExpire(phone, smsAuthCode, smsAuthCodeExpireTime);

        return response;
    }

    public LoginResponseDto verifySMS(LoginRequestDto requestDto) {
        // 인증에 실패한 경우
        if (isSMSAuthCodeWrong(requestDto)) {
            // 인증번호가 만료된 경우
            if (!isSMSAuthCodeExpired(requestDto)) {
                throw new RestApiException(LoginErrorCode.AUTHCODE_EXPIRED);
            }
            // 인증번호가 다른 경우
            else {
                throw new RestApiException(LoginErrorCode.AUTHCODE_WRONG);
            }
        }

        // 신규 사용자인 경우 전화번호로만 save 진행
        Member member;
        if (!memberRepository.existsByPhone(requestDto.getPhone())) {
            memberManageService.save(requestDto.getPhone());
        }
        // 기존 사용자의 경우 save 안함
        member = memberRepository.findByPhone(requestDto.getPhone()).orElseThrow(() ->
                new RestApiException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Long logInMemberId = member.getMemberId();
        String foundMemberId = String.valueOf(logInMemberId);
        return loginService.login(foundMemberId);
    }

    private boolean isSMSAuthCodeWrong(LoginRequestDto requestDto) {
        return !(redisUtil.hasData(requestDto.getPhone()) && redisUtil.getData(requestDto.getPhone()).equals(requestDto.getSmsAuthCode()));
    }

    private boolean isSMSAuthCodeExpired(LoginRequestDto requestDto) {
        return redisUtil.hasData(requestDto.getPhone());
    }

    // 테스트시 사용할 토큰 발급용 메소드 (운영시 삭제)
    public LoginResponseDto provideTestToken(Long memberId) {
        String stringMemberId = String.valueOf(memberId);
        return loginService.login(stringMemberId);
    }

}