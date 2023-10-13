package com.errabi.SimpleNote.web.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@Data
public class UserDto {
    private Long id ;
    @NotEmpty(message = "UserName required")
    private String username;
    @Email
    @NotEmpty(message = "Email required")
    private String email;
    private String password;
    //private List<NoteDto> notes ;
    private List<Long> noteIds;

}
// Specification bean validation search for annotation
// Impl : hibernate validator