package com.neighborhood.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class EmergencyContactInputDto {
    @Schema(description = "긴급 연락망에 들어갈 이름", example = "이승주")
    private String name;

    @Schema(description = "긴급 연락망에 들어갈 연락처", example = "01012345678")
    private String phone;
}
