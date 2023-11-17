package com.neighborhood.domain.todayquestion.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class TodayQuestionDto {

    @Data
    public static class TodayQuestion {

        @Schema(description = "해당 질문의 식별자", example = "1")
        private Long questionId;
        @Schema(description = "오늘의 질문 내용", example = "Q. 우리 가족 중 가장 이성적인 사람은 누구인가요?")
        private String content;
        @Schema(description = "아직 답변하지 않았을 때, placeholder에 넣을 내용", example = "사소한 것도 상관 없어요!")
        private String subText;
        @Schema(description = "해당 회원의 답변 여부", example = "true")
        private Boolean isAnswered;

        public TodayQuestion(Long questionId, String content, String subText, Boolean isAnswered) {
            this.questionId = questionId;
            this.content = content;
            this.subText = subText;
            this.isAnswered = isAnswered;
        }
    }

    @Data
    public static class Answer {

        @Schema(description = "질문 식별자", example = "1")
        private Long questionId;
        @Schema(description = "답변 내용", example = "OO라고 생각합니다!")
        private String content;
    }
}
