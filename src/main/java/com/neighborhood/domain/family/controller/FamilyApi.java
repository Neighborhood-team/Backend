package com.neighborhood.domain.family.controller;

import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.global.Schema.ErrorSchema;
import com.neighborhood.global.config.ResponseApiMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Validated
public interface FamilyApi {
    @Operation(summary = "존재하는 가족에 사용자 추가", description = "해당 회원을 가족코드로 찾은 가족에 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "추가 성공, ResponseData에 추가된 사용자 id 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    ResponseEntity<ResponseApiMessage> saveToExistingFamily(
            @Parameter(in = ParameterIn.PATH, description = "가족코드", required = true, schema = @Schema()) @PathVariable("familyCode") String familyCode,
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);


    @Operation(summary = "새 가족 생성, 사용자를 만들어진 가족에 추가", description = "해당 사용자를 가족 최초 생성자로 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "가족 생성, 사용자 등록 성공, 사용자 전체 정보를 담은 dto 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    MemberResponseDto saveToNewFamily(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);


    @Operation(summary = "가족 내의 모든 사용자 조회", description = "해당 가족 내의 모든 사용자를 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공, 가족 내 사용자의 전체 정보를 담은 dto list 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    List<MemberResponseDto> getAllMembers(
            @Parameter(in = ParameterIn.PATH, description = "가족코드", required = true, schema = @Schema()) @PathVariable("familyCode") String familyCode);
}