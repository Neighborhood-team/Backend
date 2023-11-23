package com.neighborhood.domain.pretest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@Data
public class ResultSaveRequestDto {
    @Schema(description = "우리가족유형검사 문항별 점수 배열 (총 28문제)", example = "[1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]")
    List<Integer> scores;
}
