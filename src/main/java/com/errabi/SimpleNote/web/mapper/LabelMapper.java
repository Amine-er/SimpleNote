package com.errabi.SimpleNote.web.mapper;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.web.model.LabelDto;
import com.errabi.SimpleNote.web.model.UserDto;
import org.mapstruct.Mapper;
@Mapper
public interface LabelMapper {
    Label toEntity(LabelDto labelDto);
    LabelDto toDto(Label label);
}
