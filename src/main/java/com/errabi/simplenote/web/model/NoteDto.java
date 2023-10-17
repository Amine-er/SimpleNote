package com.errabi.simplenote.web.model;

import com.errabi.simplenote.entities.Reminder;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class NoteDto {
    private Long id;
    @NotBlank(message = "Title is mandatory")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title ;
    @NotBlank(message = "Content is mandatory")
    private String content ;
    private Boolean isArchived ;
    private List<Long> labelIds;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Reminder> reminders;
}
