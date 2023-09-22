package com.bompracavalo.api.models.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findBySub(String sub);

    Optional<UserEntity> findByEmail(String email);
}
