package com.errabi.SimpleNote.web.model;

import lombok.Data;

@Data
public class NoteDto {
    private Long id;
    private String title ;
    private String content ;
    private Boolean isArchived ;
}
