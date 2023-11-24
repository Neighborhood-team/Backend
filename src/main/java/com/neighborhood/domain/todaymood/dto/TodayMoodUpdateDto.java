package com.neighborhood.domain.todaymood.dto;

import com.neighborhood.domain.todaymood.entity.TodayMood;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TodayMoodUpdateDto {
    @Schema(description = "오늘의 기분 id", example = "1")
    private Long mood_id;

    //happy, calm, excited, sad, Sad, anger, anxiety
    @Schema(description = "기분 상태", example = "happy, calm, excited, sad, Sad, anger, anxiety")
    private String mood;

    @Schema(description = "오늘의 기분에 작성한 메세지", example = "오늘은 전화하지 마쇼. 회의가 있소")
    private String message;

   public Long updateToDto(TodayMood todayMood, TodayMoodUpdateDto todayMoodUpdateDto){
       todayMood.setMood(todayMoodUpdateDto.getMood());
       todayMood.setMessage(todayMoodUpdateDto.getMessage());
       return todayMood.getTodayMood_id();
   }
}
