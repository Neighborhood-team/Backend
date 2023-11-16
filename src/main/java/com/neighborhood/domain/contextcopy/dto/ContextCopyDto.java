package com.neighborhood.domain.contextcopy.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;


@Getter
@Data
public class ContextCopyDto {

    private String context;

    private String context_title;

    @QueryProjection
    public ContextCopyDto(String context, String context_title) {
        this.context = context;
        this.context_title = context_title;
    }
}
