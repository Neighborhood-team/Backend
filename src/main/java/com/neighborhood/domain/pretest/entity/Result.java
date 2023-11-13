package com.neighborhood.domain.pretest.entity;

import com.neighborhood.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;
    @Column
    private String resultType;

    @Column
    private String resultCode;

    @ElementCollection
    Map<String, Integer> typeScores;

    @CreatedDate
    private LocalDateTime createdDate;

    @OneToOne(mappedBy = "result")
    private TypeImage typeImage;

    @Column
    private Long typeNumber;
    /*
    프론트단 요청으로 유형별 Long값 value 제공

    0: 강한 아이
    1: 어색한 아이
    2: 헤매는 아이
    3: 얼어붙은 아이
    4: 목마른 아이
    5: 혼란스러운 아이
    6: 숨겨진 아이
    */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    public void calculateScores(Map<String, Integer> typeScores) {
        this.typeScores = typeScores;
    }

    public void updateTypeAndDate(String resultType) {
        this.resultType = resultType;
        this.createdDate = LocalDateTime.now();
    }

    public void updateResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public static Result createResult() {
        Result result = new Result();
        return result;
    }

    public void addTypeImage(TypeImage typeImage) {
        this.typeImage = typeImage;
    }

    public void setTypeNumber(Long typeNumber) {
        this.typeNumber = typeNumber;
    }
}
