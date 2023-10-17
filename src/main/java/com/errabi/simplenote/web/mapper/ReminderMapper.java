package com.errabi.simplenote.web.mapper;

import com.errabi.simplenote.entities.Reminder;
import com.errabi.simplenote.web.model.ReminderDto;
import org.mapstruct.Mapper;

@Mapper
public interface ReminderMapper {
    Reminder toEntity(ReminderDto reminderDto);
    ReminderDto toDto(Reminder reminder);
}
