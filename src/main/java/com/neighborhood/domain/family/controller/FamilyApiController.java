package com.neighborhood.domain.family.controller;

import com.neighborhood.domain.family.service.FamilyApiService;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.MemberErrorCode;
import com.neighborhood.global.util.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("family")
public class FamilyApiController extends BaseController implements FamilyApi {
    private final FamilyApiService familyApiService;

    @PostMapping("/existing/{familyCode}/{memberId}")
    public ResponseEntity<?> saveToExistingFamily(@PathVariable String familyCode, @PathVariable Long memberId) {
        Boolean exists = familyApiService.addMemberToExistingFamily(memberId, familyCode);

        if(exists) {
            return new ResponseEntity<>(familyCode, HttpStatus.OK);
        }
        else {
            throw new RestApiException(MemberErrorCode.FAMILY_NOT_FOUND);
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