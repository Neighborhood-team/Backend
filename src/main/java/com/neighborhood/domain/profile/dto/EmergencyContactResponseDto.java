package com.neighborhood.domain.profile.dto;

import com.neighborhood.domain.profile.entity.EmergencyContact;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class EmergencyContactResponseDto {
    @Schema(description = "긴급 연락망 id", example = "1")
    Long emergencyContactId;

    @Schema(description = "사용자 id", example = "1")
    Long memberId;
    @Schema(description = "긴급 연락망의 이름", example = "이승주")
    String name;
    @Schema(description = "긴급 연락망의 연락처", example = "01012345678")
    String phone;

    public EmergencyContactResponseDto(EmergencyContact emergencyContact) {
        this.emergencyContactId = emergencyContact.getEmergencyContactId();
        this.name = emergencyContact.getName();
        this.phone = emergencyContact.getPhone();
        this.memberId = emergencyContact.getMember().getMemberId();
    }
}
