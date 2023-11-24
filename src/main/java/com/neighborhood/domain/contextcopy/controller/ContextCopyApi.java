package com.neighborhood.domain.contextcopy.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
public interface ContextCopyApi {
    @Operation(summary = "복사할 문장 호출", description = "가족 타입에 맞추어 존재하는 질문 반환")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "복사할 문장 조회 성공, 역할군에 맞는 문장 전송"),
            @ApiResponse(responseCode = "401", description = "복사할 문장 조회 실패"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class))),
            @ApiResponse(responseCode = "500", description = "서버 내부 에러", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorSchema.class)))})
    @GetMapping(produces = {"application/json"})
    ResponseEntity<ResponseApiMessage> getContext(
            @Parameter(in = ParameterIn.DEFAULT, description = "사용자 가족 유형", required = true, schema = @Schema()) @RequestBody String familyType);
}
