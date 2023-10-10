package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.web.model.LabelDto;
import com.errabi.SimpleNote.web.model.ReminderDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface LabelMapper {
    Label toEntity(LabelDto labelDto);
    LabelDto toDto(Label label);
    void updateFromDto(LabelDto labelDto, @MappingTarget Label label);
    ReminderDto map(Reminder value);
}
