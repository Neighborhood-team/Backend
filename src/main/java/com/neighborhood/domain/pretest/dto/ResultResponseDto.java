package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.Result;
import lombok.Getter;

@Getter
public class ResultResponseDto {
    Long userId;
    Long resultId;
    String userType;

    public ResultResponseDto(Result result, Long userId) {
        this.userId = userId;
        this.resultId = result.getResultId();
        this.userType = String.valueOf(result.getUserType());
    }
}
