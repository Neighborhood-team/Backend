package com.neighborhood.domain.todaymood.entity;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.todaymood.dto.TodayMoodDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TodayMood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todayMood_id;

    //happy, calm, excited, sad, Sad, anger, anxiety
    private String mood;

    private String message;

    @CreatedDate
    private LocalDateTime create_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public TodayMood(String mood, String message, Member member) {
        this.mood = mood;
        this.message = message;
        this.member = member;
    }

}
