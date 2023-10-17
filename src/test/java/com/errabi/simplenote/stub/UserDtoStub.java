package com.errabi.simplenote.stub;

import com.errabi.simplenote.web.model.UserDto;

public class UserDtoStub {

    public static UserDto getUserDto(){
        return null;
      /*return   UserDto.builder()
                        .id(1L)
                        .email("email.test@gmail.com")
                        .password("admin")
                        .username("admin")
                        .notes(new ArrayList<>())
                        .build();*/
    }
}
