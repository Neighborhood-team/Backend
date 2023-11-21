package com.neighborhood.domain.todaymood.entity;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class TodayMood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todayMood_id;

    private String mood;

    private String message;

    @CreatedDate
    private LocalDateTime create_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
