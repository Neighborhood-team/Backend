package com.neighborhood.domain.todaymood.controller;

import com.neighborhood.domain.firebase.FCMNotificationRequestDto;
import com.neighborhood.domain.firebase.FCMService;
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
    private final FCMService fcmService;

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
    
    //여기서 알림을 보내는데 나중에 기획 title, body 받게되면 그 값으로 변경하고 회원 id값만 받아도 괜찮을듯?
    @PostMapping("/notification")
    public String sendNotification(@RequestBody FCMNotificationRequestDto requestDto){
        return fcmService.sendNotification(requestDto);
    }

}
