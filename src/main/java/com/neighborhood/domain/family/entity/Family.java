package com.neighborhood.domain.family.entity;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.global.util.RandomCodeUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "family")
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FamilyTypeScore> familyTypeScores = new ArrayList<>();

    public Family() {
        this.familyCode = RandomCodeUtil.generateResultCode(6);
    }
}
