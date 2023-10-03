package com.neighborhood.domain.users.repository;

import com.neighborhood.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
