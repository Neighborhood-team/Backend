package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.profile.dto.ScheduleDto;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

public interface ScheduleApi {

    @Operation(summary = "스케줄 추가", description = "입력 내용을 바탕으로 해당 회원/가족의 스케줄을 추가한다. 시간은 yyyy-MM-dd 형식으로 입력해주시면 됩니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 메세지", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageOnlyResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(value = "/schedules", produces = { "application/json" })
    MessageOnlyResponseDto addSchedule(
            Principal principal,
            @Parameter(in = ParameterIn.DEFAULT, description = "스케줄 정보 입력 내용", required=true, schema=@Schema()) @RequestBody ScheduleDto.InputForm form);


    @Operation(summary = "스케줄 조회", description = "해당 회원의 스케줄 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "스케줄 목록", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScheduleDto.ScheduleList.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(value = "/schedules", produces = { "application/json" })
    ScheduleDto.ScheduleList getSchedules(
            Principal principal);
}
