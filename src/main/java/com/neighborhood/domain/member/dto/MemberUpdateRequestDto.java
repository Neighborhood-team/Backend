package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.FamilyRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Data
public class MemberUpdateRequestDto {
    @Schema(description = "사용자 이름", example = "강다운")
    String name;
    @Schema(description = "사용자의 가족관계", example = "MOM, SON, DAD, DAUGHTER 중 하나")
    FamilyRole familyRole;
    @Schema(description = "사용자 생년월일", example = "1999-01-01")
    String birthDate;


}
