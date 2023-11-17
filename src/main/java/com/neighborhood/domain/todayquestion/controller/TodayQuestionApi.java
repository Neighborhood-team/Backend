package com.neighborhood.domain.todayquestion.controller;

import com.neighborhood.domain.todayquestion.dto.UpdateTodayQuestionDto;
import com.neighborhood.global.Schema.ErrorSchema;
import com.neighborhood.global.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Validated
public interface TodayQuestionApi {

    @Operation(summary = "오늘의 질문 갱신", description = "해당 회원 가족의 오늘의 질문 갱신 시간 확인 후 오늘 날짜 아니면 새 질문으로 갱신")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "갱신 여부", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTodayQuestionDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(value = "/{memberId}/today-question", produces = { "application/json" })
    UpdateTodayQuestionDto updateTodayQuestion(@Parameter(in = ParameterIn.PATH, description = "멤버의 id", required=true, schema=@Schema()) @PathVariable("memberId") Long memberId);
}
