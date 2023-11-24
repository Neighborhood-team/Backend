package com.neighborhood.domain.todaymood.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class TodayMoodListDto {
    @Schema(description = "오늘의 기분 id", example = "1")
    private Long moodId;
    //happy, calm, excited, sad, Sad, anger, anxiety
    @Schema(description = "기분 상태", example = "happy, calm, excited, sad, Sad, anger, anxiety")
    private String mood;
    @Schema(description = "오늘의 기분에 작성한 메세지", example = "오늘은 전화하지 마쇼. 회의가 있소")
    private String message;
    @Schema(description = "사용자 이름", example = "황철순")
    private String memberName;
    @Schema(description = "사용자 생년월일", example = "1999-07-08")
    private String birthDate;
    @Schema(description = "사용자 가족 역할군", example = "DAD,MOM,SON ...")
    private String memberRole;

    @Builder
    public TodayMoodListDto(Long moodId,String mood, String message, String memberName, String memberRole, String birthDate) {
        this.moodId = moodId;
        this.mood = mood;
        this.message = message;
        this.memberName = memberName;
        this.memberRole = memberRole;
        this.birthDate = birthDate;
    }
}
