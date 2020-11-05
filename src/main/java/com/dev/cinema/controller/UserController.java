package com.dev.cinema.controller;

import com.dev.cinema.dto.UserResponseDto;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.models.User;
import com.dev.cinema.service.UserService;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            return mapper.convertToResponseDto(user.get());
        } else {
            throw new DataProcessingException("Can`t find user by email "
                    + email);
        }
    }
}
