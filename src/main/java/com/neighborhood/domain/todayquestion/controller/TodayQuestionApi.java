package com.neighborhood.domain.todayquestion.controller;

import com.neighborhood.domain.todayquestion.dto.TodayQuestionDto;
import com.neighborhood.global.dto.MessageOnlyResponseDto;
import com.neighborhood.global.Schema.ErrorSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Validated
public interface TodayQuestionApi {

    @Operation(summary = "오늘의 질문 갱신", description = "해당 회원 가족의 오늘의 질문 갱신 시간 확인 후 오늘 날짜 아니면 새 질문으로 갱신")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "갱신 여부", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageOnlyResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(value = "/today-question", produces = { "application/json" })
    MessageOnlyResponseDto updateTodayQuestion(Principal principal);


    @Operation(summary = "오늘의 질문 조회", description = "해당 회원 가족의 오늘의 질문 조회. 답변 여부에 따라 응답 차이 있음.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "오늘의 질문 내용/placeholder/해당 회원의 답변 유무", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayQuestionDto.TodayQuestion.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(value = "/today-question", produces = { "application/json" })
    TodayQuestionDto.TodayQuestion getTodayQuestion(Principal principal);


    @Operation(summary = "오늘의 질문 답변 작성", description = "해당 회원 가족의 오늘의 질문 답변 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청. ex. 이미 답변했는데 다시 해당 api 호출", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(value = "/today-question/answer", produces = { "application/json" })
   ResponseEntity<?> addAnswer(
            Principal principal,
            @Parameter(in = ParameterIn.DEFAULT, description = "답변 내용", required=true, schema=@Schema()) @RequestBody TodayQuestionDto.AnswerForm body);


    @Operation(summary = "회원 가족의 오늘의 질문 답변 조회", description = "회원 가족의 오늘의 질문 답변 조회. 가족 답변 여부에 따라 응답이 조금씩 다름")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변에 대한 정보가 담긴 dto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayQuestionDto.AnswersOfFamily.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(value = "/today-question/family-answers", produces = { "application/json" })
    TodayQuestionDto.AnswersOfFamily getFamilyAnswers(
            Principal principal,
            @Parameter(in = ParameterIn.QUERY, description = "조회 날짜(ex.2023-11-11)", example = "2023-11-11", required=true) @RequestParam(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);


    @Operation(summary = "가족에게 오늘의 질문 답변 제안하기", description = "회원 가족에게 질문 답변을 제안하는 푸시 알림을 전송한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변에 대한 정보가 담긴 dto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageOnlyResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(value = "/today-question/send-push/{memberId}", produces = { "application/json" })
    MessageOnlyResponseDto sendAnswerRequestPush(
            Principal principal,
            @Parameter(in = ParameterIn.PATH, description = "푸시알림 대상 회원 식별자 id", example = "1", required=true) @PathVariable("memberId") Long memberId);
}
