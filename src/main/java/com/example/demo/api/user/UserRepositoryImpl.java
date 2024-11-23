package com.example.demo.api.user;

import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;
import com.example.demo.core.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<User> getById(UUID id) {
        return UserMapper.fromJpaToDomain(jpaUserRepository.findById(id));
    }

    @Override
    public User updateById(UUID id, UpdateUserDTO user) {
        UserEntity userEntity = UserEntity.builder().name(user.name()).email(user.email()).password(user.password()).id(id).build();
        return UserMapper.fromJpaToDomain(jpaUserRepository.save(userEntity));
    }

    @Override
    public boolean emailExists(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public User save(UpdateUserDTO user) {
        UserEntity userEntity = UserEntity.builder().name(user.name()).email(user.email()).password(user.password()).build();
        return UserMapper.fromJpaToDomain(jpaUserRepository.save(userEntity));
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream().map(UserMapper::fromJpaToDomain).toList();
    }
}
