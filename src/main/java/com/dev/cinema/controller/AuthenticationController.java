package com.dev.cinema.controller;

import com.dev.cinema.dto.UserRequestDto;
import com.dev.cinema.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    private void register(@RequestBody UserRequestDto userDto) {
        authenticationService.register(userDto.getEmail(), userDto.getPassword());
    }
}
