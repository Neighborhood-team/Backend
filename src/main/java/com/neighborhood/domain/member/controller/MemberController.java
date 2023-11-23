package com.neighborhood.domain.member.controller;

import com.neighborhood.domain.member.dto.MemberNameResponseDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
import com.neighborhood.domain.member.service.MemberManageService;
import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController extends BaseController {

    private final static int SUCCESS_CODE = 200;
    private final MemberManageService memberManageService;

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> delete(@PathVariable Long memberId) {
        Long deletedMemberId = memberManageService.delete(memberId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Member deleted", deletedMemberId);
    }

    @GetMapping("/name/{familyCode}")
    public MemberNameResponseDto findFirstMemberInFamily(@PathVariable String familyCode) {
        return memberManageService.findFirstMemberInFamily(familyCode);
    }

    @PutMapping("/setInfo/{memberId}")
    public MemberResponseDto update(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberManageService.update(memberId, requestDto);
    }
}