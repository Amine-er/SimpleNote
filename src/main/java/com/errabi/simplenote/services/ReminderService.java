package com.errabi.simplenote.services;

import com.errabi.simplenote.entities.Reminder;
import com.errabi.simplenote.repositories.ReminderRepository;
import com.errabi.simplenote.web.mapper.ReminderMapper;
import com.errabi.simplenote.web.model.ReminderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderService {
    private final ReminderRepository reminderRepository;
    private final ReminderMapper reminderMapper;

    public ReminderDto save(ReminderDto dto){

        Reminder reminder = reminderMapper.toEntity(dto);
        reminderRepository.save(reminder);

        return dto ;
    }
}
