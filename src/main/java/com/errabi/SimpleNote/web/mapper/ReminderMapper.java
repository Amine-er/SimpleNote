package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.web.model.ReminderDto;
import com.errabi.SimpleNote.web.model.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReminderMapper {
    Reminder toEntity(ReminderDto reminderDto);
    ReminderDto toDto(Reminder reminder);
}
