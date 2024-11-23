package com.example.demo.core.user.application.use_cases;

import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;
import com.example.demo.core.user.domain.exceptions.UserAlreadyExists;
import com.example.demo.core.user.domain.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateUserUseCase {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UpdateUserDTO user) throws UserAlreadyExists {
        if (this.userRepository.emailExists(user.email())) {
            throw new UserAlreadyExists("User with email " + user.email() + " already exists");
        }

        return this.userRepository.save(user);
    }
}
