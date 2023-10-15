package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.NoteService;
import com.errabi.SimpleNote.services.UserService;
import com.errabi.SimpleNote.web.model.NoteDto;
import com.errabi.SimpleNote.web.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "Users Management", description = "Operations related to user management")
@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final NoteService noteService;
    @Operation(summary = "Get notes by user ID")
    @GetMapping("/users/{userId}/notes")
    public ResponseEntity<List<NoteDto>> getNotesByUserId(
            @PathVariable @Parameter(description = "ID of the user to retrieve notes for",
            required = true) Long userId){
                List<NoteDto> notes = noteService.findByUserId(userId);
                return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    @Operation(summary = "Get user by ID")
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable @Parameter(description = "ID of the user to retrieve",
            required = true) Long id) {
                return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Get user by username")
    @GetMapping("/users/name/{username}")
    public ResponseEntity<UserDto> getUserByUsername(
            @PathVariable @Parameter(description = "Username of the user to retrieve",
            required = true) String username) {
                return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }
    @Operation(summary = "Get all users")
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @Operation(summary = "Create a new user")
    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(
            @RequestBody @Valid
            @Parameter(description = "User details to be created. Username, email, and password are required fields.")
            UserDto userDto){
                return new ResponseEntity<>( userService.save(userDto),HttpStatus.CREATED);
    }
    @Operation(summary = "Update an existing user")
    @PutMapping("/users")
    public ResponseEntity<UserDto> UpdateUser(
            @RequestBody @Valid
            @Parameter(description = "User details to be updated. ID, username, email, and password are required fields.")
            UserDto userDto){
                return new ResponseEntity<>( userService.updateUser(userDto),HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "Delete a user by ID")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable @Parameter(description = "ID of the user to delete",
            required = true) Long id){
                userService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
    }
}