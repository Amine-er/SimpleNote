package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Reminder;
import com.errabi.SimpleNote.repositories.LabelRepository;
import com.errabi.SimpleNote.repositories.ReminderRepository;
import com.errabi.SimpleNote.web.mapper.LabelMapper;
import com.errabi.SimpleNote.web.mapper.ReminderMapper;
import com.errabi.SimpleNote.web.model.LabelDto;
import com.errabi.SimpleNote.web.model.ReminderDto;
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
