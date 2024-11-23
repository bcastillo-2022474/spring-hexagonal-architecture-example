package com.example.demo.api.user;

import com.example.demo.core.shared.domain.exceptions.InvalidProperty;
import com.example.demo.core.user.domain.exceptions.UserAlreadyExists;
import com.example.demo.core.user.domain.exceptions.UserNotFound;
import com.example.demo.core.user.application.dto.UpdateUserDTO;
import com.example.demo.core.user.domain.entities.User;
import com.example.demo.core.user.application.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        try {
            User user = this.userService.getById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDTO user) {
        try {
            User updatedUser = this.userService.updateById(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (InvalidProperty e) {
            return ResponseEntity.badRequest().build();
        } catch (UserNotFound e) {
            return ResponseEntity.notFound().build();
        } catch (UserAlreadyExists e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        try {
            this.userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody UpdateUserDTO user) {
        try {
            User createdUser = this.userService.create(user);
            return ResponseEntity.ok(createdUser);
        } catch (UserAlreadyExists e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (InvalidProperty e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAll());
    }
}
