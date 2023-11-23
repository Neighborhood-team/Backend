package com.neighborhood.domain.todaymood.dto;

import com.neighborhood.domain.todaymood.entity.TodayMood;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TodayMoodUpdateDto {

    private Long id;

    //happy, calm, excited, sad, Sad, anger, anxiety
    private String mood;

    private String message;

   public Long updateToDto(TodayMood todayMood, TodayMoodUpdateDto todayMoodUpdateDto){
       todayMood.setMood(todayMoodUpdateDto.getMood());
       todayMood.setMessage(todayMoodUpdateDto.getMessage());
       return todayMood.getTodayMood_id();
   }
}
