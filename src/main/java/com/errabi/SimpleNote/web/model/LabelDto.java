package com.errabi.SimpleNote.web.model;

import com.errabi.SimpleNote.entities.Note;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.List;

@Data
public class LabelDto {
    private Long id;
    private String name;
    private String color;
    @JsonIgnore
    private List<NoteDto> notes;
}
