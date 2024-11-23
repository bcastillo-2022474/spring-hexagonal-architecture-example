package com.example.demo.core.user.application.use_cases;

import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;
import com.example.demo.core.user.domain.exceptions.UserAlreadyExists;
import com.example.demo.core.user.domain.exceptions.UserNotFound;
import com.example.demo.core.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateUserUseCase {
    private final UserRepository userRepository;

    public UpdateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User execute(UUID id, UpdateUserDTO user) throws UserAlreadyExists, UserNotFound {
        User userFound = this.userRepository.getById(id).orElseThrow(() -> new UserNotFound("User with id " + id + " does not exist"));
        boolean isNewEmail = !userFound.getEmail().equals(user.email());

        if (this.userRepository.emailExists(user.email()) && isNewEmail) {
            throw new UserAlreadyExists("The email " + user.email() + " already is registered");
        }

        // Update user
        return this.userRepository.updateById(id, user);
    }
}
