package com.errabi.SimpleNote.web.model;

import com.errabi.SimpleNote.entities.Reminder;
import lombok.Data;

import java.util.List;

@Data
public class NoteDto {
    private Long id;
    private String title ;
    private String content ;
    private Boolean isArchived ;
    private List<Reminder> reminders;
}
