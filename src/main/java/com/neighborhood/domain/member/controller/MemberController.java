package com.neighborhood.domain.member.controller;

import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.service.MemberManageService;
import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.domain.member.dto.MemberSaveRequestDto;
import com.neighborhood.global.config.ResponseApiMessage;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController extends BaseController {

    private final static int SUCCESS_CODE = 200;
    private final MemberManageService memberManageService;

    @DeleteMapping("delete/{memberId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long memberId) {
        Long deletedMemberId = memberManageService.delete(memberId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Member deleted", deletedMemberId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMember() {
        throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }

    @GetMapping("/name/{familyCode}")
    public String findFirstMemberInFamily(@PathVariable String familyCode) {
        String firstMemberName = memberManageService.findFirstMemberInFamily(familyCode);

        return firstMemberName;
    }
}