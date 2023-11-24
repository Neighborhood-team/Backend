package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Data
public class MemberResponseDto {
    @Schema(description = "사용자 식별자(pk)", example = "1")
    Long memberId;
    @Schema(description = "사용자 이름", example = "강다운")
    String name;
    @Schema(description = "사용자 전화번호, 로그인시 ID", example = "01012345678")
    String phone;
    @Schema(description = "사용자 가족관계", example = "MOM, SON, DAD, DAUGHTER 중 하나")
    String familyRole;
    @Schema(description = "사용자가 속해있는 가족의 가족코드", example = "123456")
    String familyCode;
    @Schema(description = "사용자 생년월일", example = "1999-01-01")
    String birthDate;
    @Schema(description = "사용지 firebase 토큰", example = "암호화된 토큰")
    String fcmTocken;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.familyRole = String.valueOf(member.getFamilyRole());
        this.familyCode = member.getFamily().getFamilyCode();
        this.birthDate = String.valueOf(member.getBirthDate());
        this.fcmTocken = member.getFcmToken();
    }
}
