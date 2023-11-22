package com.neighborhood.domain.todaymood.controller;

import com.neighborhood.domain.todaymood.dto.TodayMoodDto;
import com.neighborhood.domain.todaymood.service.TodayMoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("todaymood")
public class TodayMoodController {

    private final TodayMoodService todayMoodService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody TodayMoodDto todayMoodDto) {
        Long moodId = todayMoodService.saveTodayMood(todayMoodDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moodId);
    }


}
