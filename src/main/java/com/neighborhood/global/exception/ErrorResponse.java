package com.neighborhood.global.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * 실제 에러 응답 시 body에 담길 클래스
 */
@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    @Schema(description = "HTTP 상태 코드", example = "500")
    private final String code;
    @Schema(description = "에러 메세지", example = "서버 내부 에러, 리소스 존재 x, ...")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<ValidationError> errors;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class ValidationError {
        private final String field;
        private final String message;

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }

}
