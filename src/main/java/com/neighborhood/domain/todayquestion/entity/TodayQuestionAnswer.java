package com.neighborhood.domain.todayquestion.entity;

import com.neighborhood.domain.family.entity.Family;
import com.neighborhood.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "today_question_answer")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodayQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column
    private String content;

    @Column
    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private TodayQuestion todayQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public TodayQuestionAnswer(String content, LocalDate createdDate, TodayQuestion todayQuestion, Family family, Member member) {
        this.content = content;
        this.createdDate = createdDate;
        this.todayQuestion = todayQuestion;
        this.family = family;
        this.member = member;
    }
}
