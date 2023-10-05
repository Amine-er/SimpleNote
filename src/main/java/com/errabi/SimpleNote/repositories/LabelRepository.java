package com.errabi.SimpleNote.repositories;

import com.errabi.SimpleNote.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelRepository extends JpaRepository<Label, Long> {
}
