package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class MemberNameResponseDto {
    @Schema(description = "사용자 이름", example = "강다운")
    String name;

    public MemberNameResponseDto(Member member) {
        this.name = member.getName();
    }
}
