package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
public class MemberResponseDto {
    Long memberId;
    String name;
    String phone;
    String familyRole;
    String familyCode;
    String birthDate;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.familyRole = String.valueOf(member.getFamilyRole());
        this.familyCode = member.getFamily().getFamilyCode();
        this.birthDate = String.valueOf(member.getBirthDate());
    }
}
