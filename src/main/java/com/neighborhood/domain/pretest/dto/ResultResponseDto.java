package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.Result;
import lombok.Getter;

@Getter
public class ResultResponseDto {
    Long resultId;
    String resultType;
    String resultCode;

    public ResultResponseDto(Result result) {
        this.resultId = result.getResultId();
        this.resultType = String.valueOf(result.getResultType());
        this.resultCode = result.getResultCode();
    }
}
