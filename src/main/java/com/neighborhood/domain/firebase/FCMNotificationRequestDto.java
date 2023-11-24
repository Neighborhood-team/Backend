package com.neighborhood.domain.firebase;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetId;
    private String title;
    private String body;

    @Builder
    public FCMNotificationRequestDto(Long targetId, String title, String body) {
        this.targetId = targetId;
        this.title = title;
        this.body = body;
    }
}
