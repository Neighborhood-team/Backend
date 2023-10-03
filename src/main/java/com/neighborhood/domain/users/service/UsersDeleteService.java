package com.neighborhood.domain.users.service;

import com.neighborhood.domain.users.Users;
import com.neighborhood.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersDeleteService {
    private final UsersRepository userRepository;

    public Users findUser(Long usersId) {
        return userRepository.findById(usersId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. USERS_ID=" + usersId));
    }

    @Transactional
    public Long delete(Long usersId) {
        Users users = findUser(usersId);
        userRepository.delete(users);

        return usersId;
    }
}
