package com.neighborhood.domain.users.service;

import com.neighborhood.domain.users.Users;
import com.neighborhood.domain.users.repository.UsersRepository;
import com.neighborhood.domain.users.dto.UsersSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersSaveService {
    private final UsersRepository usersRepository;

    public Users findUser(Long usersId) {
        return usersRepository.findById(usersId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. USERS_ID=" + usersId));
    }

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        Users users = requestDto.toEntity();

        return usersRepository.save(users).getUsersId();
    }
}