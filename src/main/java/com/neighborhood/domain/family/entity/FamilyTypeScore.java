package com.neighborhood.domain.family.entity;

import com.neighborhood.domain.pretest.entity.TestType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "family_type_score")
@Getter
@NoArgsConstructor
public class FamilyTypeScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scoreId;

    @Enumerated(EnumType.STRING)
    private TestType testType;

    @Column
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "family_id")
    private Family family;

    public FamilyTypeScore(TestType testType, Family family) {
        this.testType = testType;
        this.family = family;
        this.score = 0;
    }
}
