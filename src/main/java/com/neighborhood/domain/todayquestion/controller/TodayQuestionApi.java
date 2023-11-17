package com.neighborhood.domain.todayquestion.controller;

import com.neighborhood.domain.todayquestion.dto.TodayQuestionDto;
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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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


    @Operation(summary = "오늘의 질문 조회", description = "해당 회원 가족의 오늘의 질문 조회. 답변 여부에 따라 응답 차이 있음.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "오늘의 질문 내용/placeholder/해당 회원의 답변 유무", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayQuestionDto.TodayQuestion.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(value = "/{memberId}/today-question", produces = { "application/json" })
    TodayQuestionDto.TodayQuestion getTodayQuestion(@Parameter(in = ParameterIn.PATH, description = "멤버의 id", required=true, schema=@Schema()) @PathVariable("memberId") Long memberId);


    @Operation(summary = "오늘의 질문 답변 작성", description = "해당 회원 가족의 오늘의 질문 답변 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(value = "/{memberId}/today-question/answer", produces = { "application/json" })
   ResponseEntity<?> addAnswer(
            @Parameter(in = ParameterIn.PATH, description = "멤버의 id", required=true, schema=@Schema()) @PathVariable("memberId") Long memberId,
            @Parameter(in = ParameterIn.DEFAULT, description = "답변 내용", required=true, schema=@Schema()) @RequestBody TodayQuestionDto.AnswerForm body);


    @Operation(summary = "회원 가족의 오늘의 질문 답변 조회", description = "회원 가족의 오늘의 질문 답변 조회. 가족 답변 여부에 따라 응답이 조금씩 다름")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변에 대한 정보가 담긴 dto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayQuestionDto.AnswersOfFamily.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(value = "/{memberId}/today-question/family-answers", produces = { "application/json" })
    TodayQuestionDto.AnswersOfFamily getFamilyAnswers(
            @Parameter(in = ParameterIn.PATH, description = "멤버의 id", required=true, schema=@Schema()) @PathVariable("memberId") Long memberId,
            @Parameter(in = ParameterIn.QUERY, description = "조회 날짜", example = "2023-11-17", required=true, schema=@Schema()) @RequestParam("memberId") LocalDate date);
}
