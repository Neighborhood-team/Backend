package com.neighborhood.domain.pretest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
