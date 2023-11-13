package com.neighborhood.domain.pretest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestType {

    STRONG("Strong", 0L),
    AWKWARD("Awkward", 1L),
    LOST("Lost", 2L),
    FROZEN("Frozen", 3L),
    THIRSTY("Thirsty", 4L),
    CONFUSED("Confused", 5L),
    HIDDEN("Hidden", 6L)
    ;

    private final String testType;
    private final Long code;
}
