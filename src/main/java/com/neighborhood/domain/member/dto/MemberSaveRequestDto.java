package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    String phone;

    public Member toEntity() {
        return Member.builder()
                .phone(phone)
                .build();
    }
}
