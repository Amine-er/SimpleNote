package com.errabi.SimpleNote.repositories;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findAllByUserId(Long id);
    List<Note> findByUserId(Long userId);
}
