package com.neighborhood.domain.todaymood.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TodayMoodListDto {

    private Long moodId;
    //happy, calm, excited, sad, Sad, anger, anxiety
    private String mood;

    private String message;

    private String memberName;

    private String memberRole;

    @Builder
    public TodayMoodListDto(Long moodId,String mood, String message, String memberName, String memberRole) {
        this.moodId = moodId;
        this.mood = mood;
        this.message = message;
        this.memberName = memberName;
        this.memberRole = memberRole;
    }
}
