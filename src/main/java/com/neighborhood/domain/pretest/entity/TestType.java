package com.neighborhood.domain.pretest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Strong : 강한 아이
// Awkward : 어색한 아이
// Lost : 헤매는 아이
// Frozen : 얼어붙은 아이
// Thirsty : 목 마른 아이
// Confused : 혼란스러운 아이
// Hidden : 숨겨진 아이

@Getter
@AllArgsConstructor
public enum TestType {

    STRONG("Strong", 0L),
    AWKWARD("Awkward", 0L),
    LOST("Lost", 0L),
    FROZEN("Frozen", 0L),
    THIRSTY("Thirsty", 0L),
    CONFUSED("Confused", 0L),
    HIDDEN("Hidden", 0L)
    ;

    private final String testType;
    private final Long code;
}
