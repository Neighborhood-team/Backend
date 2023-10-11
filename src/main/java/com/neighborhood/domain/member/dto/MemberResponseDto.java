package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class MemberResponseDto {
    Long memberId;
    String phone;
    String createdDate;
    String modifiedDate;

    public MemberResponseDto(Member member) {
        this.memberId = member.getMemberId();
        this.phone = member.getPhone();
        this.createdDate = member.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        this.modifiedDate = member.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
}
