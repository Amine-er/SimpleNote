package com.errabi.simplenote.web.mapper;

import com.errabi.simplenote.entities.Label;
import com.errabi.simplenote.entities.Note;
import com.errabi.simplenote.entities.Reminder;
import com.errabi.simplenote.web.model.LabelDto;
import com.errabi.simplenote.web.model.ReminderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface LabelMapper {
    Label toEntity(LabelDto labelDto);
    //@Mapping(target = "notes", ignore = true)
    @Mapping(source = "notes", target = "noteIds", qualifiedByName = "toNoteIdList")
    LabelDto toDto(Label label);
    @Named("toNoteIdList")
    default List<Long> toNoteIdList(List<Note> notes) {
        if (notes == null) {
            return null;
        }
        return notes.stream().map(Note::getId).collect(Collectors.toList());
    }
    void updateFromDto(LabelDto labelDto, @MappingTarget Label label);
    ReminderDto map(Reminder value);
}
