package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.web.model.NoteDto;
import com.errabi.SimpleNote.web.model.ReminderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface NoteMapper {
    Note toEntity(NoteDto noteDto);
    //List<Note> toEntityList(List<NoteDto> notes);
    @Mapping(source = "labels", target = "labelIds", qualifiedByName = "toLabelIdList")
    NoteDto toDto(Note note);
    //List<NoteDto> toDtoList(List<Note> notes);
    void updateFromDto(NoteDto noteDto, @MappingTarget Note note);
    ReminderDto map(Reminder value);
    @Named("toLabelIdList")
    default List<Long> toLabelIdList(List<Label> labels) {
        if (labels == null) {
            return null;
        }
        return labels.stream().map(Label::getId).collect(Collectors.toList());
    }

}
