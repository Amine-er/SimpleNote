package com.errabi.SimpleNote.repositories;

import com.errabi.SimpleNote.entities.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
}
