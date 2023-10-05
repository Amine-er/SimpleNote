package com.errabi.SimpleNote.web.model;

import com.errabi.SimpleNote.entities.Note;

import java.util.List;

public class LabelDto {
    private Long id;
    private String name;
    private String color;
    private List<Note> notes;
}
