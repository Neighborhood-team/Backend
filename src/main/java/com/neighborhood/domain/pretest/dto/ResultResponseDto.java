package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.entity.Result;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class ResultResponseDto {
    @Schema(description = "우리가족유형검사 결과 id", example = "1")
    Long resultId;
    @Schema(description = "결과 유형", example = "STRONG, AWKWARD, LOST, FROZEN, THIRSTY, CONFUSED, HIDDEN 중 하나")
    String resultType;
    @Schema(description = "검사 결과 코드 (6자리 영문대문자+숫자 혼합)", example = "N1B31G")
    String resultCode;
    @Schema(description = "결과 유형에 맞는 png 이미지의 다운로드 url", example = "https://capstone-project-bucket.s3.ap-northeast-2.amazonaws.com/type-images/Awkward.png")
    String imageUrl;
    @Schema(description = "결과 유형 식별용 숫자", example = "    0: 강한 아이, 1: 어색한 아이, 2: 헤매는 아이, 3: 얼어붙은 아이, 4: 목마른 아이, 5: 혼란스러운 아이, 6: 숨겨진 아이")
    Long typeNumber;

    public ResultResponseDto(Result result) {
        this.resultId = result.getResultId();
        this.resultType = String.valueOf(result.getResultType());
        this.resultCode = result.getResultCode();
        this.imageUrl = result.getTypeImage().getImageUrl();
        this.typeNumber = result.getTypeNumber();
    }
}