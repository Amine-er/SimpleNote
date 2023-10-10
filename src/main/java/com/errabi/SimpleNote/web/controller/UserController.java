package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.UserService;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return new ResponseEntity(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/users/name/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity(userService.findByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
         return new ResponseEntity<>( userService.save(userDto),HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>( userService.updateUser(userDto),HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

