package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.entity.Result;
import com.neighborhood.domain.pretest.entity.TypeImage;
import lombok.Getter;

@Getter
public class ResultResponseDto {
    Long resultId;
    String resultType;
    String resultCode;
    String imageUrl;

    public ResultResponseDto(Result result) {
        this.resultId = result.getResultId();
        this.resultType = String.valueOf(result.getResultType());
        this.resultCode = result.getResultCode();
        this.imageUrl = result.getTypeImage().getImageUrl();
    }
}
