package com.neighborhood.domain.todayquestion.controller;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.todayquestion.dto.TodayQuestionDto;
import com.neighborhood.global.dto.MessageOnlyResponseDto;
import com.neighborhood.domain.todayquestion.service.TodayQuestionApiService;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TodayQuestionApiController implements TodayQuestionApi {

    private final MemberRepository memberRepository;
    private final TodayQuestionApiService todayQuestionApiService;

    public MessageOnlyResponseDto updateTodayQuestion(Principal principal) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Boolean isUpdate = todayQuestionApiService.checkUpdate(member);
        String message = isUpdate ? "오늘의 질문이 갱신 되었습니다." : "오늘의 질문이 유지됩니다.";
        return new MessageOnlyResponseDto(message);
    }


    public TodayQuestionDto.TodayQuestion getTodayQuestion(Principal principal) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return todayQuestionApiService.getTodayQuestion(member);
    }

    public ResponseEntity<?> addAnswer(Principal principal, TodayQuestionDto.AnswerForm body) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return todayQuestionApiService.addAnser(member, body);
    }

    public TodayQuestionDto.AnswersOfFamily getFamilyAnswers(Principal principal, LocalDate date) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return todayQuestionApiService.getFamilyAnswers(member, date);
    }
}
