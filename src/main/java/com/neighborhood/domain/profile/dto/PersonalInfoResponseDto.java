package com.neighborhood.domain.profile.dto;

import com.neighborhood.domain.profile.entity.PersonalInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class PersonalInfoResponseDto {
    @Schema(description = "상세정보 id", example = "1")
    Long personalInfoId;

    @Schema(description = "사용자 id", example = "1")
    Long memberId;

    @Schema(description = "mbti", example = "ESFP")
    String mbti;

    @Schema(description = "혈액형", example = "B형")
    String bloodType;

    @Schema(description = "좋아하는 것", example = "초밥, 파란색, 쇼핑하기")
    String likes;

    @Schema(description = "싫어하는 것", example = "가지볶음, 출근하기")
    String hates;

    @Schema(description = "요즘 나의 관심사", example = "새로 산 게임기")
    String interests;

    @Schema(description = "좌우명", example = "그럴 수도 있지")
    String motto;

    @Schema(description = "가족 내에서 나의 역할", example = "주말에 요리하기")
    String roleInFam;

    @Schema(description = "가족과 가고 싶은 여행지", example = "보라카이")
    String placeToTrip;

    @Schema(description = "생일에 받고 싶은 선물", example = "지갑")
    String birthdayGift;

    @Schema(description = "키, 옷 사이즈, 신발 사이즈", example = "175cm, 100/34, 270")
    String mySizes;

    public PersonalInfoResponseDto(PersonalInfo personalInfo) {
        this.personalInfoId = personalInfo.getPersonalInfoId();
        this.mbti = personalInfo.getMbti();
        this.bloodType = personalInfo.getBloodType();
        this.likes = personalInfo.getLikes();
        this.hates = personalInfo.getHates();
        this.interests = personalInfo.getInterests();
        this.motto = personalInfo.getMotto();
        this.roleInFam = personalInfo.getRoleInFam();
        this.placeToTrip = personalInfo.getPlaceToTrip();
        this.birthdayGift = personalInfo.getBirthdayGift();
        this.mySizes = personalInfo.getMySizes();
        this.memberId = personalInfo.getMember().getMemberId();
    }
}
