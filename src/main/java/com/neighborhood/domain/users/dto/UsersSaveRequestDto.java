package com.neighborhood.domain.users.dto;

import com.neighborhood.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    String phone;

    public Users toEntity() {
        return Users.builder()
                .phone(phone)
                .build();
    }
}
