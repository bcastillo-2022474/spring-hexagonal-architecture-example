package com.example.demo.core.user.application.use_cases;

import com.example.demo.core.shared.domain.exceptions.InvalidProperty;
import org.springframework.stereotype.Component;

@Component
public class ValidateEmailUseCase {
    public void  execute(String email) throws InvalidProperty {
        if (!email.contains("@")) {
            throw new InvalidProperty("Email not valid, should contain `@`");
        }
    }
}
