package com.errabi.SimpleNote.web.model;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Reminder;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class NoteDto {
    private Long id;
    private String title ;
    private String content ;
    private Boolean isArchived ;
    private List<LabelDto> labels;
    private List<ReminderDto> reminders;
}
