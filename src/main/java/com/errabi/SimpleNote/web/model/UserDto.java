package com.errabi.SimpleNote.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {
    private Long id ;
    private String username;
    private String email;
    private String password;
    private List<NoteDto> notes ;
}
