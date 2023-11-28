package com.neighborhood.domain.todaymood.controller;

import com.neighborhood.domain.firebase.FCMNotificationRequestDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import com.neighborhood.global.Schema.ErrorSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
public interface TodayMoodApi {
    @Operation(summary = "오늘의 기분 저장", description = "사용자에게 입력받은 기분, 메세지등을 저장합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "오늘의 기분 저장 성공, 성공시 해당 기분의 id값 반환"),
            @ApiResponse(responseCode = "401", description = "오늘의 기분이 저장되지 않았습니다."),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    ResponseEntity<?> saveTodayMood(
            @Parameter(in = ParameterIn.DEFAULT, description = "오늘의 기분 저장에 사용될 데이터 및 회원id", required = true, schema = @Schema()) @RequestBody TodayMoodUpdateDto todayMoodDto);

    @Operation(summary = "가족 오늘의 기분", description = "해당 사용자의 가족 기분정보를 list로 제공합니다. 생년월일순으로 정렬")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가족의 기분 조회 성공, 해당 list값을 반환"),
            @ApiResponse(responseCode = "401", description = "가족의 기분 조회 실패"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    ResponseEntity<?> getFamilyMoodList(Principal principal);

    @Operation(summary = "오늘의 기분 수정", description = "사용자에게 입력받은 기분, 메세지등을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "오늘의 기분 수정 성공, 성공시 해당 기분의 id값 반환"),
            @ApiResponse(responseCode = "401", description = "오늘의 기분이 수정되지 않았습니다."),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(produces = {"application/json"})
    ResponseEntity<?> updateTodayMood(
            @Parameter(in = ParameterIn.DEFAULT, description = "오늘의 기분 수정에 사용될 정보", required = true, schema = @Schema()) @RequestBody TodayMoodUpdateDto todayMoodDto);

    @Operation(summary = "알림전송기능", description = "알림버튼을 누른 가족에게 알림을 전송시키는 기능입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "알림이 정상적으로 전송되었습니다."),
            @ApiResponse(responseCode = "401", description = "알림이 전송되지 않았습니다."),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    String sendNotification(
            @Parameter(in = ParameterIn.DEFAULT, description = "알림에 사용될 제목과 내용을 받는 데이터", required = true, schema = @Schema()) @RequestBody FCMNotificationRequestDto requestDto);
}
