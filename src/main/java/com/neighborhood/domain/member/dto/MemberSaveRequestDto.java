package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.FamilyRole;
import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {
    String name;
    String phone;
    String email;
    FamilyRole familyRole;
}
