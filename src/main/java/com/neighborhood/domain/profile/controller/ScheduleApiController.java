package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.domain.profile.dto.ScheduleDto;
import com.neighborhood.domain.profile.service.ScheduleService;
import com.neighborhood.global.dto.MessageOnlyResponseDto;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ScheduleApiController implements ScheduleApi {

    private final MemberRepository memberRepository;
    private final ScheduleService scheduleService;

    public MessageOnlyResponseDto addSchedule(Principal principal, ScheduleDto.InputForm form) {

        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        scheduleService.addSchedule(member, form);

        return new MessageOnlyResponseDto("일정 추가 성공");
    }


    public ScheduleDto.ScheduleList getSchedules(Principal principal) {
        Member member = memberRepository.findById(Long.parseLong(principal.getName()))
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        return scheduleService.getSchedules(member);
    }
}
