package com.errabi.simplenote.web.mapper;

import com.errabi.simplenote.entities.Note;
import com.errabi.simplenote.entities.Reminder;
import com.errabi.simplenote.entities.User;
import com.errabi.simplenote.web.model.ReminderDto;
import com.errabi.simplenote.web.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    User toEntity(UserDto userDto);
    //@Mapping(target = "notes", ignore = true)
    @Mapping(source = "notes", target = "noteIds", qualifiedByName = "toNoteIdList")
    UserDto toDto(User user);

    @Named("toNoteIdList")
    default List<Long> toNoteIdList(List<Note> notes) {
        if (notes == null) {
            return null;
        }
        return notes.stream().map(Note::getId).collect(Collectors.toList());
    }

    void updateFromDto(UserDto userDto, @MappingTarget User user);
    ReminderDto map(Reminder value);

}
