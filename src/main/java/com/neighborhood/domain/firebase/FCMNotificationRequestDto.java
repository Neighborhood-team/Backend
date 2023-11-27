package com.neighborhood.domain.firebase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetId;
    //기분 최신화 요청
    private String title;
    //가족이 ㅁㅁㅁ님의 현재 기분을 궁금해해요!
    private String body;

    @Builder
    public FCMNotificationRequestDto(Long targetId, String title, String body) {
        this.targetId = targetId;
        this.title = title;
        this.body = body;
    }
}
