package com.errabi.SimpleNote.controller;

import com.errabi.SimpleNote.stub.UserDtoStub;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest extends BaseTest {

    @Test  //JUnit
    void saveUserTest() throws Exception {
        mockMvc.perform(post("/simple-note/v1/users")
                        .content(asJsonString(UserDtoStub.getUserDto()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
