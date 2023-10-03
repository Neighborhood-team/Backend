package com.neighborhood.domain.users.controller;

import com.neighborhood.domain.pretest.controller.BaseController;
import com.neighborhood.domain.users.dto.UsersSaveRequestDto;
import com.neighborhood.domain.users.service.UsersDeleteService;
import com.neighborhood.domain.users.service.UsersSaveService;
import com.neighborhood.global.config.ResponseApiMessage;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController extends BaseController {

    private final static int SUCCESS_CODE = 200;
    private final UsersSaveService userSaveService;
    private final UsersDeleteService userDeleteService;

    @PostMapping("/save")
    public ResponseEntity<ResponseApiMessage> save(@RequestBody UsersSaveRequestDto requestDto) {
        Long savedUsersId = userSaveService.save(requestDto);

        return sendResponseHttpByJson(SUCCESS_CODE, "User saved", savedUsersId);
    }

    @DeleteMapping("delete/{usersId}")
    public ResponseEntity<ResponseApiMessage> delete(@PathVariable Long usersId) {
        Long deletedUsersId = userDeleteService.delete(usersId);

        return sendResponseHttpByJson(SUCCESS_CODE, "User deleted", deletedUsersId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsers() {
        throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}