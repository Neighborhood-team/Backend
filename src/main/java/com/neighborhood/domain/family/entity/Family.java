package com.neighborhood.domain.family.entity;

import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.global.util.RandomCodeUtil;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "family")
public class Family {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @Column
    private String code;

    @OneToMany(mappedBy = "family")
    private List<Member> members = new ArrayList<>();

    public Family() {
        this.code = RandomCodeUtil.generateResultCode(6);
    }
}
