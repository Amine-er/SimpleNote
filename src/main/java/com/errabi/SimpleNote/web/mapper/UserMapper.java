package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.web.model.ReminderDto;
import com.errabi.SimpleNote.web.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
    void updateFromDto(UserDto userDto, @MappingTarget User user);
    ReminderDto map(Reminder value);

}
