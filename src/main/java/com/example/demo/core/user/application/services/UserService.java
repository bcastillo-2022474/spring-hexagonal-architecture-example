package com.example.demo.core.user.application.services;

import com.example.demo.core.shared.domain.exceptions.InvalidProperty;
import com.example.demo.core.user.application.use_cases.CreateUserUseCase;
import com.example.demo.core.user.application.use_cases.UpdateUserUseCase;
import com.example.demo.core.user.application.use_cases.ValidateEmailUseCase;
import com.example.demo.core.user.domain.exceptions.UserAlreadyExists;
import com.example.demo.core.user.domain.exceptions.UserNotFound;
import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;
import com.example.demo.core.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserService {
    private final UserRepository userRepository;
    private final ValidateEmailUseCase validateEmailUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    public UserService(UserRepository userRepository, ValidateEmailUseCase validateEmailUseCase, UpdateUserUseCase updateUserUseCase, CreateUserUseCase createUserUseCase) {
        this.userRepository = userRepository;
        this.validateEmailUseCase = validateEmailUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getById(UUID id) throws UserNotFound {
        return this.userRepository.getById(id).orElseThrow(UserNotFound::new);
    }

    public User updateById(UUID id, UpdateUserDTO user) throws InvalidProperty, UserNotFound, UserAlreadyExists {
        validateEmailUseCase.execute(user.email());
        return updateUserUseCase.execute(id, user);
    }

    public void deleteById(UUID id) throws UserNotFound {
        this.userRepository.getById(id).orElseThrow(UserNotFound::new);
        this.userRepository.deleteById(id);
    }

    public User create(UpdateUserDTO user) throws InvalidProperty, UserAlreadyExists {
        this.validateEmailUseCase.execute(user.email());
        return createUserUseCase.execute(user);
    }
}
