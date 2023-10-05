package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.UserService;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/User/{id}")
    UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}

