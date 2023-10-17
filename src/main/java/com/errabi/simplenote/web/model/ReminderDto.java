package com.errabi.simplenote.web.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReminderDto {
    private Long id;
    private LocalDate time;
}
