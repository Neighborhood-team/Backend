package com.neighborhood.domain.member.controller;

import com.neighborhood.domain.member.dto.MemberNameResponseDto;
import com.neighborhood.domain.member.dto.MemberResponseDto;
import com.neighborhood.domain.member.dto.MemberUpdateRequestDto;
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

@Validated
public interface MemberApi {
    @Operation(summary = "사용자 삭제", description = "해당 사용자를 삭제 (응답에서 사용할 data 없음)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 삭제 성공, 삭제된 사용자 id 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @DeleteMapping(produces = {"application/json"})
    ResponseEntity<?> delete(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);


    @Operation(summary = "가족 최초 생성 사용자 찾기", description = "해당 가족 내에서 생성시간이 가장 빠른 사용자의 이름 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공, 가족 최초 생성자 이름을 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    MemberNameResponseDto findFirstMemberInFamily(
            @Parameter(in = ParameterIn.PATH, description = "가족코드", required = true, schema = @Schema()) @PathVariable("familyCode") String familyCode);


    @Operation(summary = "사용자 개인정보 등록", description = "회원가입 페이지에서 입력받은 사용자 개인정보를 해당 사용자에게 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "사용자 개인정보 등록 성공, 사용자 전체 정보를 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(produces = {"application/json"})
    MemberResponseDto update(
            @Parameter(in = ParameterIn.PATH, description = "가족코드", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId,
            @Parameter(in = ParameterIn.DEFAULT, description = "사용자 개인정보", required = true, schema = @Schema()) @RequestBody MemberUpdateRequestDto requestDto);

}
