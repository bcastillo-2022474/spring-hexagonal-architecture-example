package com.example.demo.core.user.domain.repositories;

import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> getById(UUID id);
    User updateById(UUID id, UpdateUserDTO user);
    boolean emailExists(String email);
    void deleteById(UUID id);
    User save(UpdateUserDTO user);
    List<User> findAll();

}
