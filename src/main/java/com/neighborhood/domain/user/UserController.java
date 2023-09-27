package com.neighborhood.domain.user;

import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser() {
        throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
    }
}