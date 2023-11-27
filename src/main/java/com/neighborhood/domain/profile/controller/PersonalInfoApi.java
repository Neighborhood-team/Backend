package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.member.dto.MemberNameResponseDto;
import com.neighborhood.domain.profile.dto.PersonalInfoInputDto;
import com.neighborhood.domain.profile.dto.PersonalInfoResponseDto;
import com.neighborhood.domain.profile.entity.PersonalInfo;
import com.neighborhood.global.Schema.ErrorSchema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
public interface PersonalInfoApi {
    @Operation(summary = "사용자 상세정보 등록", description = "사용자가 입력한 상세정보를 해당 사용자 프로필에 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공, 상세정보를 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(produces = {"application/json"})
    PersonalInfoResponseDto updatePersonalInfo(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId,
            @Parameter(in = ParameterIn.DEFAULT, description = "사용자가 입력한 상세정보", required = true, schema = @Schema()) @RequestBody PersonalInfoInputDto personalInfoInputDto);


    @Operation(summary = "사용자 상세정보 조회", description = "해당 사용자의 프로필 상세정보 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공, 해당 사용자의 상세정보를 담은 dto 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    PersonalInfoResponseDto getPersonalInfo(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);
}
