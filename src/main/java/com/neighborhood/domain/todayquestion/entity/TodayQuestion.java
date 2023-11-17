package com.neighborhood.domain.todayquestion.entity;

import com.neighborhood.domain.pretest.entity.TestType;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "today_question")
@Getter
public class TodayQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column
    private String content;

    @Column
    private String subText;

    @Enumerated(EnumType.STRING)
    private TestType type;

}
