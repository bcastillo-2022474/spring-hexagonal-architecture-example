package com.example.demo.api.user;

import com.example.demo.core.user.domain.entities.User;

import java.util.Optional;

public class UserMapper {
    public static Optional<User> fromJpaToDomain(Optional<UserEntity> userEntity) {
        return userEntity.map(user -> new User(user.getId(), user.getName(), user.getEmail(), user.getPassword()));
    }

    public static Optional<UserEntity> fromDomainToJpa(Optional<User> user) {
        return user.map(u -> new UserEntity(u.getId(), u.getName(), u.getEmail(), u.getPassword()));
    }

    public static UserEntity fromDomainToJpa(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }

    public static User fromJpaToDomain(UserEntity userEntity) {
        return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());
    }
}
