package com.neighborhood.domain.contextcopy.controller;


import com.neighborhood.domain.contextcopy.service.ContextCopyService;
import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;


@RestController
@RequiredArgsConstructor
@RequestMapping("contextcopy")
public class ContextCopyController extends BaseController {

    private final ContextCopyService contextCopyService;
    private final static int SUCCESS_CODE = 200;
    private final static int FAIL_CODE = 400;

    /**
     * 복사할 문장 호출 - 가족 타입을 받아서 해당 타입에 맞는 질문을 랜덤으로 반환
     * [POST] contextcopy/get-context
     * @return ResponseEntity
     */
    @GetMapping("/get-context")
    public ResponseEntity<ResponseApiMessage> getContext(@RequestBody String familyType){
        return sendResponseHttpByJson(SUCCESS_CODE, "get context", contextCopyService.getRandomContext(familyType));
    }
}
