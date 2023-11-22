package com.neighborhood.domain.family.controller;

import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("family")
public class FamilyApiController extends BaseController {
    private final FamilyApiService familyApiService;
    private static int SUCCESS_CODE = 200;
    private static int NOT_FOUND_CODE = 404;

    @PostMapping("/existing/{familyCode}/{memberId}")
    public ResponseEntity<ResponseApiMessage> saveToExistingFamily(@PathVariable String familyCode, @PathVariable Long memberId) {
        Boolean exists = familyApiService.addMemberToExistingFamily(memberId, familyCode);

        if(exists) {
            return sendResponseHttpByJson(SUCCESS_CODE, "Member added to existing family", familyCode);
        }
        else {
            return sendResponseHttpByJson(NOT_FOUND_CODE, "Family not found", familyCode);
        }
    }

    @PostMapping("/new/{memberId}")
    public MemberResponseDto saveToNewFamily(@PathVariable Long memberId) {
        MemberResponseDto responseDto = familyApiService.addMemberToNewFamily(memberId);

        return responseDto;
    }
}