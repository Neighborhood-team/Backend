package com.neighborhood.domain.contextcopy.dto;


import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class ContextCopyDto {
    @Schema(description = "문장 복사기 내용", example = "오늘 힘든일이 있었죠? 모두 힘든시간 있답니다. ....")
    private String context;
    @Schema(description = "문장 복사기 제목", example = "엄마, 요즘 힘든일은 없으세요?")
    private String context_title;

    @QueryProjection
    public ContextCopyDto(String context, String context_title) {
        this.context = context;
        this.context_title = context_title;
    }
}
