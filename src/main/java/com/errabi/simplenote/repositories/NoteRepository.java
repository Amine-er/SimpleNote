package com.errabi.simplenote.repositories;

import com.errabi.simplenote.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>, JpaSpecificationExecutor<Note>,
        PagingAndSortingRepository<Note, Long>{
    public List<Note> findAllByUserId(Long id);
    List<Note> findByUserId(Long userId);
}
