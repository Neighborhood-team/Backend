package com.neighborhood.domain.pretest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestType {

    STRONG("Strong"),
    AWKWARD("Awkward"),
    LOST("Lost"),
    FROZEN("Frozen"),
    THIRSTY("Thirsty"),
    CONFUSED("Confused"),
    HIDDEN("Hidden")
    ;

    private final String testType;
}
