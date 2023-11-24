package com.neighborhood.domain.profile.entity;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.pretest.entity.TestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Schedule(LocalDate startDate, LocalDate endDate, String content, Member member) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.member = member;
    }
}
