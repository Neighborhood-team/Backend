package com.neighborhood.domain.pretest.controller;

import com.neighborhood.domain.pretest.dto.ResultSaveRequestDto;
import com.neighborhood.domain.pretest.service.ResultDeleteService;
import com.neighborhood.domain.pretest.service.ResultSaveService;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("pretest/result")
public class ResultController extends BaseController {
    private final static int SUCCESS_CODE = 200;
    private final ResultSaveService resultSaveService;
    private final ResultDeleteService resultDeleteService;

    @PostMapping("/save/{usersId}")
    public ResponseEntity<ResponseApiMessage> save(@PathVariable Long usersId, @RequestBody ResultSaveRequestDto requestDto) {
        Long savedResultId = resultSaveService.save(usersId, requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "Pretest Result saved", savedResultId);
    }

    @DeleteMapping("delete/{resultId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long resultId) {
        Long deletedResultId = resultDeleteService.delete(resultId);

        return sendResponseHttpByJson(SUCCESS_CODE, "Pretest Result deleted", deletedResultId);
    }
}
