package com.neighborhood.domain.contextcopy.controller;


import com.neighborhood.domain.contextcopy.service.ContextCopyService;
import com.neighborhood.global.util.BaseController;
import com.neighborhood.global.config.ResponseApiMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("contextcopy")
public class ContextCopyController extends BaseController implements ContextCopyApi {

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
