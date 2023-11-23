package com.neighborhood.domain.todaymood.controller;

import com.neighborhood.domain.todaymood.dto.TodayMoodDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodListDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import com.neighborhood.domain.todaymood.service.TodayMoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("todaymood")
public class TodayMoodController {

    private final TodayMoodService todayMoodService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTodayMood(@RequestBody TodayMoodDto todayMoodDto) {
        Long moodId = todayMoodService.saveTodayMood(todayMoodDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moodId);
    }

    @GetMapping("/get-family/{memberId}")
    public ResponseEntity<?> getFamilyMoodList(@PathVariable Long memberId) {
        List<TodayMoodListDto> moods = todayMoodService.getFamilyMoodList(memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(moods);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTodayMood(@RequestBody TodayMoodUpdateDto todayMoodUpdateDto) {
        Long moodId = todayMoodService.updateTodayMood(todayMoodUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moodId);
    }

}
