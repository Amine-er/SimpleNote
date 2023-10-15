package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.NoteService;
import com.errabi.SimpleNote.web.model.NoteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
@Tag(name = "Notes Management", description = "Operations related to note management")
@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    @Operation(summary = "Retrieve a note by ID")
    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNoteById(
            @PathVariable @Parameter(description = "ID of the note to retrieve",
            required = true) Long id) {
                /*String value =  request.getHeader("app");
                log.info("Header key {} value is : {}","app",value);*/
                return new ResponseEntity<>(noteService.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Retrieve all notes")
    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> notes = noteService.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
    @Operation(summary = "Create a new note")
    @PostMapping("/notes")
    public ResponseEntity<NoteDto> createNote(
            @RequestBody @Valid
            @Parameter(description = "Note details to be created. Title and Content are required fields.")
            NoteDto noteDto,
            @RequestParam @Parameter(description = "ID of the user creating the note",
            required = true) Long userId){
                return new ResponseEntity<>( noteService.create(noteDto, userId), HttpStatus.CREATED);
    }
    @Operation(summary = "Update an existing note")
    @PutMapping("/notes")
    public ResponseEntity<NoteDto> updateNote(
            @RequestBody @Valid
            @Parameter(description = "Note details to be updated. ID, Title, and Content are required fields.")
            NoteDto noteDto){
                return new ResponseEntity<>(noteService.update(noteDto), HttpStatus.OK);
    }
    @Operation(summary = "Delete a note by ID")
    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable @Parameter(description = "ID of the note to delete",
            required = true) Long noteId){
                noteService.delete(noteId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}