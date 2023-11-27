package com.neighborhood.domain.family.controller;

import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.global.util.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("family")
public class FamilyApiController extends BaseController implements FamilyApi {
    private final FamilyApiService familyApiService;
    private static final int SUCCESS_CODE = 200;
    private static final int NOT_FOUND_CODE = 404;

    @PostMapping("/existing/{familyCode}/{memberId}")
    public ResponseEntity<ResponseApiMessage> saveToExistingFamily(@PathVariable String familyCode, @PathVariable Long memberId) {
        Boolean exists = familyApiService.addMemberToExistingFamily(memberId, familyCode);

        if(exists) {
            return sendResponseHttpByJson(SUCCESS_CODE, "사용자가 가족코드(" + familyCode + ")인 가족에 추가되었습니다.", familyCode);
        }
        else {
            return sendResponseHttpByJson(NOT_FOUND_CODE, "해당 가족이 존재하지 않습니다. 가족코드(" + familyCode + ").", familyCode);
        }
    }

    @PostMapping("/new/{memberId}")
    public MemberResponseDto saveToNewFamily(@PathVariable Long memberId) {
        MemberResponseDto responseDto = familyApiService.addMemberToNewFamily(memberId);

        return responseDto;
    }

    @GetMapping("/allMember/{familyCode}")
    public List<MemberResponseDto> getAllMembers(@PathVariable String familyCode) {
        return familyApiService.getAllMembersInFamily(familyCode);
    }
}