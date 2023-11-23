package com.neighborhood.domain.todaymood.dto;

import com.neighborhood.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class TodayMoodDto {

    //happy, calm, excited, sad, Sad, anger, anxiety
    private String mood;

    private String message;

    private Long memberId;

    @Builder
    public TodayMoodDto(String mood, String message, Long memberId) {
        this.mood = mood;
        this.message = message;
        this.memberId = memberId;
    }
}
