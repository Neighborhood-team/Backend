package com.neighborhood.domain.pretest.controller;

import com.neighborhood.domain.pretest.dto.ResultResponseDto;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.service.ResultManageService;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("pretest/result")
public class ResultController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final ResultManageService resultManageService;

    @PostMapping("/")
    public ResultResponseDto save(@RequestBody ResultSaveRequestDto requestDto) {
        ResultResponseDto responseDto = resultManageService.save(requestDto);

        return responseDto;
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long resultId) {
        Long deletedResultId = resultManageService.delete(resultId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Pretest Result deleted", deletedResultId);
    }

    @GetMapping("/test-count")
    public Long getTestCount() {
        Long testCount = resultManageService.getTestCount();
        return testCount;
    }
}
