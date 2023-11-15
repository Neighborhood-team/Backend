package com.neighborhood.domain.family.controller;

import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberSaveRequestDto;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("family")
public class FamilyApiController extends BaseController {
    private final FamilyApiService familyApiService;
    private static int SUCCESS_CODE = 200;
    private static int NOT_FOUND_CODE = 404;

    @PostMapping("/existing/{familyCode}")
    public ResponseEntity<ResponseApiMessage> saveToExistingFamily(@RequestBody MemberSaveRequestDto requestDto, @PathVariable String familyCode) {
        Boolean exists = familyApiService.addMemberToExistingFamily(requestDto, familyCode);

        if(exists) {
            return sendResponseHttpByJson(SUCCESS_CODE, "Member added to existing family", familyCode);
        }
        else {
            return sendResponseHttpByJson(NOT_FOUND_CODE, "Family not found", familyCode);
        }
    }

    @PostMapping("/new")
    public MemberResponseDto saveToNewFamily(@RequestBody MemberSaveRequestDto requestDto) {
        MemberResponseDto responseDto = familyApiService.addMemberToNewFamily(requestDto);

        return responseDto;
    }
}