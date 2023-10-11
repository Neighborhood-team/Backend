package com.neighborhood.domain.pretest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
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

    public void calculateScores(Map<String, Integer> typeScores) {
        this.typeScores = typeScores;
    }

    public void updateType(String resultType) {
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
}
