package com.neighborhood.domain.member.dto;

import com.neighborhood.domain.member.entity.FamilyRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class MemberCheckDuplicateParentsDto {
    @Schema(description = "중복을 체크할 사용자의 id", example = "1")
    private Long memberId;
    @Schema(description = "중복을 체크할 사용자의 가족관계", example = "MOM 또는 DAD")
    private FamilyRole familyRole;
}
