package com.neighborhood.domain.family.entity;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.pretest.entity.TestType;
import com.neighborhood.domain.todayquestion.entity.TodayQuestionAnswer;
import com.neighborhood.global.util.RandomCodeUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family")
@Getter
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column
    private String familyCode;

    @Enumerated(EnumType.STRING)
    private TestType todayQuestionType;

    @Column
    private Integer questionNum;

    @Column
    private LocalDateTime questionUpdatedTime;

    @OneToMany(mappedBy = "family")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyTypeScore> familyTypeScores = new ArrayList<>();

    @OneToMany(mappedBy = "family")
    private List<TodayQuestionAnswer> todayQuestionAnswers = new ArrayList<>();

    public Family() {
        this.familyCode = RandomCodeUtil.generateResultCode(6);
    }

    public void setTodayQuestionType(TestType todayQuestionType) {
        this.todayQuestionType = todayQuestionType;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public void setQuestionUpdateTime(LocalDateTime questionUpdateTime) {
        this.questionUpdatedTime = questionUpdateTime;
    }
}
