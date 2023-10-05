package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.web.mapper.NoteMapper;
import com.errabi.SimpleNote.web.model.NoteDto;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteDto save(NoteDto dto){

        Note note = noteMapper.toEntity(dto);
        noteRepository.save(note);

        return dto ;
    }
    public NoteDto findById(Long id){
        Optional<Note> optionalNote =  noteRepository.findById(id);
        if(optionalNote.isPresent()){
            NoteDto dto = noteMapper.toDto(optionalNote.get());
            return dto;
        }else{
            throw new RuntimeException("Note not found");
        }
    }

    public List<NoteDto> findAllNotesForUser(Long userId) {
        List<Note> notes = noteRepository.findAllByUserId(userId);
        return noteMapper.toDtoList(notes);
    }
}
