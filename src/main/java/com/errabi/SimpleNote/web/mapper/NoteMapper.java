package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.web.model.NoteDto;
import com.errabi.SimpleNote.web.model.ReminderDto;
import com.errabi.SimpleNote.web.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface NoteMapper {
    Note toEntity(NoteDto noteDto);
    List<Note> toEntityList(List<NoteDto> notes);
    NoteDto toDto(Note note);
    List<NoteDto> toDtoList(List<Note> notes);
    void updateFromDto(NoteDto noteDto, @MappingTarget Note note);
    ReminderDto map(Reminder value);

}
