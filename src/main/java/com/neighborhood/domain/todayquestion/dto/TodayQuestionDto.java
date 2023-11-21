package com.neighborhood.domain.todayquestion.dto;


import com.neighborhood.domain.member.entity.FamilyRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @NoArgsConstructor
    public static class AnswerForm {

        @Schema(description = "질문 식별자", example = "1")
        private Long questionId;
        @Schema(description = "답변 내용", example = "OO라고 생각합니다!")
        private String content;

        public AnswerForm(Long questionId, String content) {
            this.questionId = questionId;
            this.content = content;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AnswersOfFamily {

        @Schema(description = "한명 이상 답변 여부", example = "true")
        private Boolean isAnyoneAnswered;
        @Schema(description = "한명 이상 답변 시 해당 답변에 해당하는 질문 식별자", example = "1")
        private Long questionId;
        @Schema(description = "한명 이상 답변 시 해당 답변에 해당하는 질문", example = "오늘 가장 기뻤던 일은 무엇인가요?")
        private String questionContent;
        @Schema(description = "가족 구성원의 답변 리스트")
        private List<AnswerOfMember> answers;
    }

    @Data
    @AllArgsConstructor
    public static class AnswerOfMember {

        @Schema(description = "답변 여부", example = "true")
        private Boolean isAnswered;
        @Schema(description = "회원의 가족 내 역할", example = "SON")
        private FamilyRole familyRole;
        @Schema(description = "회원 이름", example = "김철수")
        private String memberName;
        @Schema(description = "답변 식별자", example = "1")
        private Long answerId;
        @Schema(description = "답변 내용", example = "ㅇㅇ 같습니다!")
        private String content;
    }

}
