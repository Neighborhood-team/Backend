package com.neighborhood.domain.pretest.dto;

import com.neighborhood.domain.pretest.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
public class ResultSaveRequestDto {
    List<Integer> scores;
}
