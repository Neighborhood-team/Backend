package com.neighborhood.domain.todayquestion.controller;

import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.todayquestion.dto.TodayQuestionDto;
import com.neighborhood.domain.todayquestion.dto.UpdateTodayQuestionDto;
import com.neighborhood.domain.todayquestion.service.TodayQuestionApiService;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodayQuestionApiController implements TodayQuestionApi {

    private final MemberRepository memberRepository;
    private final TodayQuestionApiService todayQuestionApiService;

    public UpdateTodayQuestionDto updateTodayQuestion(Long memberId) {

        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));
        Boolean isUpdate = todayQuestionApiService.checkUpdate(member);

        String message = isUpdate ? "오늘의 질문이 갱신 되었습니다." : "오늘의 질문이 유지됩니다.";
        return new UpdateTodayQuestionDto(message);
    }


    public TodayQuestionDto.TodayQuestion getTodayQuestion(Long memberId) {

        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return todayQuestionApiService.getTodayQuestion(member);
    }

    public ResponseEntity<?> addAnswer(Long memberId, TodayQuestionDto.Answer body) {

        Member member = memberRepository
                .findById(memberId).orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return todayQuestionApiService.addAnser(member, body);
    }
}
