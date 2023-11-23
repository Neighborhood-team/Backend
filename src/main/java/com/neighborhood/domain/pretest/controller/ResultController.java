package com.neighborhood.domain.pretest.controller;

import com.neighborhood.domain.pretest.dto.ResultResponseDto;
import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.service.ResultManageService;
import com.neighborhood.global.util.S3Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("pretest/result")
@Log4j2
public class ResultController implements PretestApi {
    private final ResultManageService resultManageService;
    private final S3Util s3Util;

    @PostMapping("/")
    public ResultResponseDto save(@RequestBody ResultSaveRequestDto requestDto) {
        ResultResponseDto responseDto = resultManageService.save(requestDto);

        return responseDto;
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<?> delete(@PathVariable Long resultId) {
        Long deletedResultId = resultManageService.delete(resultId);

        return new ResponseEntity<>(deletedResultId, HttpStatus.OK);
    }

    @GetMapping("/test-count")
    public Long getTestCount() {
        Long testCount = resultManageService.getTestCount();
        return testCount;
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) throws IOException {
        return s3Util.download("gallery-images/" + fileName);
    }
}
