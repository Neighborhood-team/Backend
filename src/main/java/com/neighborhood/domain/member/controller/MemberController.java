package com.neighborhood.domain.member.controller;

import com.neighborhood.domain.member.dto.MemberCheckDuplicateParentsDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
import com.neighborhood.domain.member.service.MemberManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController implements MemberApi {

    private final MemberManageService memberManageService;

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> delete(@PathVariable Long memberId) {
        Long deletedMemberId = memberManageService.delete(memberId);

        return new ResponseEntity<>(deletedMemberId, HttpStatus.OK);
    }

    @GetMapping("/name/{familyCode}")
    public MemberResponseDto findFirstMemberInFamily(@PathVariable String familyCode) {
        return memberManageService.findFirstMemberInFamily(familyCode);
    }

    @PutMapping("/setInfo/{memberId}")
    public MemberResponseDto update(@PathVariable Long memberId, @RequestBody MemberUpdateRequestDto requestDto) {
        return memberManageService.update(memberId, requestDto);
    }

    @GetMapping("/checkParents")
    public Boolean checkDuplicateParents(@RequestBody MemberCheckDuplicateParentsDto memberCheckDuplicateParentsDto) {
        return memberManageService.checkDuplicateParents(memberCheckDuplicateParentsDto);
    }

    @GetMapping("/")
    public MemberResponseDto getMemberInfo(@RequestHeader("token") String token) {
        return memberManageService.getMemberInfo(token);
    }
}