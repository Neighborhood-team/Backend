package com.neighborhood.global.Schema;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorSchema {

    @Schema(description = "HTTP 상태 코드", example = "INTERNAL_SERVER_ERROR / NOT_FOUND / ...")
    private String code;
    @Schema(description = "에러 메세지", example = "서버 내부 에러, 리소스 존재 x / ...")
    private String message;

}
