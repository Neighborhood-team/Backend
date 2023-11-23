package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberNameResponseDto {
    String name;

    public MemberNameResponseDto(Member member) {
        this.name = member.getName();
    }
}
