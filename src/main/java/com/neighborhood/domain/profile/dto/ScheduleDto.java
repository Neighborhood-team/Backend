package com.neighborhood.domain.profile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

import java.time.LocalDate;
import java.util.List;

public class ScheduleDto {

    @Data
    @NoArgsConstructor
    public static class InputForm {

        @Schema(description = "개인 or 가족 전체 대상 여부", example = "\"ME\" 또는 \"FAMILY\"")
        private String target;
        @Schema(description = "스케줄 시작 날짜", example = "2023-01-01", type = "string")
        private LocalDate startDate;
        @Schema(description = "스케줄 종료 날짜", example = "2023-01-05", type = "string")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate endDate;
        @Schema(description = "스케줄 내용", example = "저녁 약속")
        private String content;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ScheduleList {

        @Schema(description = "정렬된 일정 리스트")
        private List<InquiryForm> schedules;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InquiryForm {

        @Schema(description = "스케줄 식별자", example = "1")
        private Long id;
        @Schema(description = "스케줄 시작 날짜", example = "2023-01-01")
        private String startDate;
        @Schema(description = "스케줄 종료 날짜", example = "2023-01-05")
        private String endDate;
        @Schema(description = "스케줄 내용", example = "저녁 약속")
        private String content;
        @Schema(description = "진행 중 여부", example = "true")
        private Boolean isOngoing;
    }

}
