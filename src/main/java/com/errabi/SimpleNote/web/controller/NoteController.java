package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.NoteService;
import com.errabi.SimpleNote.web.model.NoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id, HttpServletRequest request) {
        String value =  request.getHeader("app");
        log.info("Header key {} value is : {}","app",value);
        return new ResponseEntity<>(noteService.findById(id), HttpStatus.OK);
    }


    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getAllNotes() {
        List<NoteDto> notes = noteService.findAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto, @RequestParam Long userId){
        return new ResponseEntity<>( noteService.create(noteDto, userId), HttpStatus.CREATED);
    }

    @PutMapping("/notes")
    public ResponseEntity<NoteDto> updateNote(@RequestBody NoteDto noteDto) {
        return new ResponseEntity<>(noteService.update(noteDto), HttpStatus.OK);
    }

    @DeleteMapping("/notes/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId) {
        noteService.delete(noteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
