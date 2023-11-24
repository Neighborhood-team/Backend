package com.neighborhood.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageOnlyResponseDto {

    @Schema(description = "처리 내용 설명", example = "질문 유지 / 새로운 질문으로 갱신")
    private String message;
}
