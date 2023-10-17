package com.errabi.simplenote.web.controller;

import com.errabi.simplenote.services.LabelService;
import com.errabi.simplenote.web.model.LabelDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@Tag(name = "Labels Management", description = "Operations related to label management")
@RestController
@RequestMapping("/simple-note/v1/")
@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService;
    @Operation(summary = "Create a new label")
    @PostMapping("/labels")
    public ResponseEntity<LabelDto> createLabel(
            @RequestBody @Valid
            @Parameter(description = "Label details. Name and Color are required fields.")
            LabelDto labelDto){
                return new ResponseEntity<>(labelService.create(labelDto), HttpStatus.CREATED);
    }
    @Operation(summary = "Retrieve a label by ID")
    @GetMapping("/labels/{id}")
    public ResponseEntity<LabelDto> getLabelById(
            @PathVariable @Parameter(description = "ID of the label to retrieve",
            required = true) Long id){
                return new ResponseEntity<>(labelService.findById(id), HttpStatus.OK);
    }
    @Operation(summary = "Retrieve all labels")
    @GetMapping("/labels")
    public ResponseEntity<Page<LabelDto>> getAllLabels(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
                Page<LabelDto> labels = labelService.findAll(page, size);
                return new ResponseEntity<>(labels, HttpStatus.OK);
    }
    @Operation(summary = "Update an existing label")
    @PutMapping("/labels")
    public ResponseEntity<LabelDto> updateLabel(
            @RequestBody @Valid
            @Parameter(description = "Label details to be updated. ID, Name, and Color are required fields.")
            LabelDto labelDto) {
                return new ResponseEntity<>(labelService.update(labelDto), HttpStatus.OK);
    }
    @Operation(summary = "Delete a label by ID")
    @DeleteMapping("/labels/{labelId}")
    public ResponseEntity<Void> deleteLabel(
            @PathVariable @Parameter(description = "ID of the label to delete",
            required = true) Long labelId){
                labelService.delete(labelId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @Operation(summary = "Associate a note with a label")
    @PostMapping("/labels/{labelId}/notes/{noteId}")
    public ResponseEntity<Void> addNoteToLabel(
            @PathVariable @Parameter(description = "ID of the label", required = true) Long labelId,
            @PathVariable @Parameter(description = "ID of the note to associate", required = true) Long noteId){
                labelService.addNoteToLabel(labelId, noteId);
                return new ResponseEntity<>(HttpStatus.OK);
    }
    @Operation(summary = "Disassociate a note from a label")
    @DeleteMapping("/labels/{labelId}/notes/{noteId}")
    public ResponseEntity<Void> removeNoteFromLabel(
            @PathVariable @Parameter(description = "ID of the label", required = true) Long labelId,
            @PathVariable @Parameter(description = "ID of the note to disassociate", required = true) Long noteId){
                labelService.removeNoteFromLabel(labelId, noteId);
                return new ResponseEntity<>(HttpStatus.OK);
    }
}