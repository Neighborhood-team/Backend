package com.neighborhood.domain.pretest;

import com.neighborhood.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private String memberType;
    @OneToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ElementCollection
    Map<String, Integer> typeScores;

    public void calculateScores(Map<String, Integer> typeScores) {
        this.typeScores = typeScores;
    }

    public void updateType(String memberType) {
        this.memberType = memberType;
    }

    public static Result createResult(Member member) {
        Result result = new Result();
        result.member = member;
        member.addResult(result);

        return result;
    }
}
