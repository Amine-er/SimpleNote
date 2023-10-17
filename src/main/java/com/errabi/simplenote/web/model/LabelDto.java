package com.errabi.simplenote.web.model;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
@Builder
@Data
public class LabelDto {
    private Long id;
    @NotBlank(message = "Label name is mandatory")
    @Size(max = 100, message = "Label name must not exceed 100 characters")
    private String name;
    @NotBlank(message = "Color is mandatory")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Invalid color format. It should be a HEX color.")
    private String color;
    private List<Long> noteIds;
}
