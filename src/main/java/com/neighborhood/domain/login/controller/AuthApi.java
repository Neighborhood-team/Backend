package com.neighborhood.domain.login.controller;

import com.neighborhood.domain.login.dto.LoginRequestDto;
import com.neighborhood.domain.login.dto.LoginResponseDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.global.Schema.ErrorSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public interface AuthApi {
    @Operation(summary = "SMS 인증번호 전송", description = "해당 번호로 SMS 인증번호 전송 (응답에서 사용할 data 없음)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인증 성공 (응답에서 사용할 data 없음)"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    SingleMessageSentResponse sendSMS(
            @Parameter(in = ParameterIn.PATH, description = "인증할 전화번호", required = true, schema = @Schema()) @PathVariable("phone") String phone);


    @Operation(summary = "SMS 인증번호로 로그인", description = "SMS 인증번호를 받은 전화번호와 받은 인증번호로 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공, 토큰과 토큰의 사용자 id를 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    LoginResponseDto verifySMS(
            @Parameter(in = ParameterIn.DEFAULT, description = "로그인에 필요한 정보", required = true, schema = @Schema()) @RequestBody LoginRequestDto requestDto);


    @Operation(summary = "Access 토큰 재발급", description = "Access 토큰이 만료되었을 때 Refresh 토큰으로 Access 토큰 재발급")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "재발급 성공, 새 토큰과 토큰의 사용자 id를 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    LoginResponseDto reIssue(
                @Parameter(in = ParameterIn.HEADER, description = "Refresh 토큰", required = true, schema = @Schema()) @RequestHeader(value = "token") String token);

    @Operation(summary = "테스트용 토큰 발급 (운영 시 삭제예정)", description = "개발 단계에서 편의를 위해 사용할 테스트용 토큰 발급 (운영 시 삭제예정)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "테스트 토큰 발급 성공"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    LoginResponseDto provideTestToken(
            @Parameter(in = ParameterIn.PATH, description = "db에 이미 등록되어 있는 사용자의 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);
}