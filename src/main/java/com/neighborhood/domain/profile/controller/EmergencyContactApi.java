package com.neighborhood.domain.profile.controller;

import com.neighborhood.domain.profile.dto.EmergencyContactInputDto;
import com.neighborhood.domain.profile.dto.EmergencyContactResponseDto;
import com.neighborhood.domain.profile.dto.PersonalInfoInputDto;
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

import java.util.List;

@Validated
public interface EmergencyContactApi {
    @Operation(summary = "비상 연락망 등록", description = "사용자가 입력한 비상연락망 정보(이름, 연락처)를 해당 사용자 프로필에 등록")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공, 비상연락망 정보를 담은 dto 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    EmergencyContactResponseDto save(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId,
            @Parameter(in = ParameterIn.DEFAULT, description = "사용자가 입력한 비상연락망 정보", required = true, schema = @Schema()) @RequestBody EmergencyContactInputDto emergencyContactInputDto);


    @Operation(summary = "비상 연락망 수정", description = "사용자가 입력한 정보로 해당 사용자의 비상 연락망 수정")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공, 비상연락망 정보를 담은 dto 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(produces = {"application/json"})
    EmergencyContactResponseDto update(
            @Parameter(in = ParameterIn.PATH, description = "비상연락망 id", required = true, schema = @Schema()) @PathVariable("emergencyContactId") Long emergencyContactId,
            @Parameter(in = ParameterIn.DEFAULT, description = "수정할 비상연락망 정보", required = true, schema = @Schema()) @RequestBody EmergencyContactInputDto emergencyContactInputDto);



    @Operation(summary = "비상 연락망 조회", description = "해당 사용자의 비상 연락망을 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공, 해당 사용자의 모든 비상연락망을 담은 dto list 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    List<EmergencyContactResponseDto> getAllEmergencyContacts(
            @Parameter(in = ParameterIn.PATH, description = "사용자 id", required = true, schema = @Schema()) @PathVariable("memberId") Long memberId);
}
