package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.Result;
import lombok.Getter;

@Getter
public class ResultResponseDto {
    Long memberId;
    Long resultId;
    String memberType;

    public ResultResponseDto(Result result, Long memberId) {
        this.memberId = memberId;
        this.resultId = result.getResultId();
        this.memberType = String.valueOf(result.getMemberType());
    }
}
