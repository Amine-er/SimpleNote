package com.errabi.simplenote.repositories;

import com.errabi.simplenote.entities.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder,Long> {
}
