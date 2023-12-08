package com.neighborhood.domain.pretest.controller;

import com.neighborhood.domain.pretest.dto.ResultResponseDto;
import com.neighborhood.domain.pretest.dto.ResultResponseMemberDto;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.entity.Result;
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

import java.io.IOException;
import java.security.Principal;

@Validated
public interface PretestApi {
    @Operation(summary = "우리가족유형검사 결과 저장", description = "우리가족유형검사 문항 별 점수 배열로 결과 저장")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결과 저장 성공, 결과 정보를 담은 dto 반환", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PostMapping(produces = {"application/json"})
    ResultResponseDto save(
            @Parameter(in = ParameterIn.DEFAULT, description = "검사 결과 점수 배열을 담은 dto", required = true, schema = @Schema()) @RequestBody ResultSaveRequestDto requestDto);


    @Operation(summary = "우리가족유형검사 결과 삭제", description = "해당 검사 결과를 삭제 (응답에서 사용할 data 없음)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검사 결과 삭제 성공, 삭제된 결과 id 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @DeleteMapping(produces = {"application/json"})
    ResponseEntity<?> delete(
            @Parameter(in = ParameterIn.PATH, description = "검사 결과 id", required = true, schema = @Schema()) @PathVariable("resultId") Long resultId);


    @Operation(summary = "우리가족유형검사 검사자 수 확인", description = "우리가족유형검사를 실시한 사용자의 총 수를 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공, 검사한 사용자 수 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    Long getTestCount();


    @Operation(summary = "우리가족유형검사 결과 유형별 이미지 다운로드", description = "해당 검사 유형의 결과 png 이미지 다운로드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "다운로드 성공, png파일 반환"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    ResponseEntity<byte[]> download(
            @Parameter(in = ParameterIn.PATH, description = "파일명(유형.png)", example = "Awkward.png", required = true, schema = @Schema()) @PathVariable("fileName") String fileName) throws IOException;

    @Operation(summary = "우리가족유형검사결과 반환", description = "사용자 정보를 바탕으로 우리가족유형검사 결과 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "우리가족유형검사결과 반환 성공"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    Result getResult(Principal principal);

    @Operation(summary = "우리가족유형검사결과에 사용자 추가", description = "암호화된 code를 바탕으로 우리가족유형검사결과에 현재 로그인한 사용자 추가")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "우리가족유형검사결과에 사용자 추가 성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @PutMapping(produces = {"application/json"})
    ResultResponseMemberDto connectResult(@Parameter(in = ParameterIn.DEFAULT, description = "사용자가 입력한 유형검사 암호화 코드", required = true) @RequestBody String  resultCode, Principal principal);

}
