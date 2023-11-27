package com.neighborhood.domain.profile.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class PersonalInfoInputDto {
    @Schema(description = "mbti", example = "ESFP")
    private String mbti;

    @Schema(description = "혈액형", example = "B형")
    private String bloodType;

    @Schema(description = "좋아하는 것", example = "초밥, 파란색, 쇼핑하기")
    private String likes;

    @Schema(description = "싫어하는 것", example = "가지볶음, 출근하기")
    private String hates;

    @Schema(description = "요즘 나의 관심사", example = "새로 산 게임기")
    private String interests;

    @Schema(description = "좌우명", example = "그럴 수도 있지")
    private String motto;

    @Schema(description = "가족 내에서 나의 역할", example = "주말에 요리하기")
    private String roleInFam;

    @Schema(description = "가족과 가고 싶은 여행지", example = "보라카이")
    private String placeToTrip;

    @Schema(description = "생일에 받고 싶은 선물", example = "지갑")
    private String birthdayGift;

    @Schema(description = "키, 옷 사이즈, 신발 사이즈", example = "175cm, 100/34, 270")
    private String mySizes;
}
