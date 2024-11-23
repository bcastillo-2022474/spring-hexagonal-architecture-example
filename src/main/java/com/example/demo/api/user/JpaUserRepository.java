package com.example.demo.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;




@Component
public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {

    boolean existsByEmail(String email);
}
