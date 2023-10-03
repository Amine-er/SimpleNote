package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.web.model.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toDto(User user);
}
