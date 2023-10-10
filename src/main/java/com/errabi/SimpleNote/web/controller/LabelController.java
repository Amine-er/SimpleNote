package com.errabi.SimpleNote.web.controller;

import com.errabi.SimpleNote.services.LabelService;
import com.errabi.SimpleNote.web.model.LabelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;

    @PostMapping("/labels")
    public ResponseEntity<LabelDto> createLabel(@RequestBody LabelDto labelDto) {
        return new ResponseEntity<>(labelService.create(labelDto), HttpStatus.CREATED);
    }

    @GetMapping("/labels/id/{id}")
    public ResponseEntity<LabelDto> getLabelById(@PathVariable Long id) {
        return new ResponseEntity<>(labelService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/labels")
    public ResponseEntity<List<LabelDto>> getAllLabels() {
        List<LabelDto> labels = labelService.findAll();
        return new ResponseEntity<>(labels, HttpStatus.OK);
    }

    @PutMapping("/labels")
    public ResponseEntity<LabelDto> updateLabel(@RequestBody LabelDto labelDto) {
        return new ResponseEntity<>(labelService.update(labelDto), HttpStatus.OK);
    }

    @DeleteMapping("/labels/{labelId}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long labelId) {
        labelService.delete(labelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/labels/{labelId}/addNote/{noteId}")
    public ResponseEntity<Void> addNoteToLabel(@PathVariable Long labelId, @PathVariable Long noteId) {
        labelService.addNoteToLabel(labelId, noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/labels/{labelId}/removeNote/{noteId}")
    public ResponseEntity<Void> removeNoteFromLabel(@PathVariable Long labelId, @PathVariable Long noteId) {
        labelService.removeNoteFromLabel(labelId, noteId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
