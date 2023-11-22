package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.FamilyRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    String name;
    FamilyRole familyRole;
    String birthDate;
}
