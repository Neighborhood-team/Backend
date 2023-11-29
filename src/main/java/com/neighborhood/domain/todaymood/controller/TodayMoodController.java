package com.neighborhood.domain.todaymood.controller;

import com.neighborhood.domain.firebase.FCMNotificationRequestDto;
import com.neighborhood.domain.firebase.FCMService;
import com.neighborhood.domain.todaymood.dto.TodayMoodListDto;
import com.neighborhood.domain.todaymood.dto.TodayMoodUpdateDto;
import com.neighborhood.domain.todaymood.service.TodayMoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("todaymood")
public class TodayMoodController implements TodayMoodApi{

    private final TodayMoodService todayMoodService;
    private final FCMService fcmService;

    @PostMapping("/save")
    public ResponseEntity<Long> saveTodayMood(@RequestBody TodayMoodUpdateDto todayMoodDto) {
        Long moodId = todayMoodService.saveTodayMood(todayMoodDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moodId);
    }

    @GetMapping("/get-family")
    public ResponseEntity<List<TodayMoodListDto>> getFamilyMoodList(Principal principal) {
        List<TodayMoodListDto> moods = todayMoodService.getFamilyMoodList(principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(moods);
    }

    @PutMapping("/update")
    public ResponseEntity<Long> updateTodayMood(@RequestBody TodayMoodUpdateDto todayMoodUpdateDto) {
        Long moodId = todayMoodService.updateTodayMood(todayMoodUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(moodId);
    }

    @PostMapping("/notification")
    public String sendNotification(@RequestBody FCMNotificationRequestDto requestDto){
        return fcmService.sendNotification(requestDto);
    }

}
