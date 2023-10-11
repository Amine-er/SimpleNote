package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.exception.TechnicalException;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.repositories.UserRepository;
import com.errabi.SimpleNote.web.mapper.NoteMapper;
import com.errabi.SimpleNote.web.model.NoteDto;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.errabi.SimpleNote.utils.SimpleNoteConst.NOTE_NOT_FOUND_ERROR_CODE;
import static com.errabi.SimpleNote.utils.SimpleNoteConst.NOTE_NOT_FOUND_ERROR_DESCRIPTION;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    public NoteDto create(NoteDto dto, Long userId){
        log.info("Saving note {} ..",dto);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Note note = noteMapper.toEntity(dto);
        note.setUser(user);
        Note savedNote = noteRepository.save(note);

        return noteMapper.toDto(savedNote);
    }
    public NoteDto findById(Long id){
        log.info("finding note by id ...");
        Optional<Note> optionalNote =  noteRepository.findById(id);
        if(optionalNote.isPresent()){
            NoteDto dto = noteMapper.toDto(optionalNote.get());
            return dto;
        }else{
            log.error("Note not found with id {}",id);
            throw new TechnicalException(NOTE_NOT_FOUND_ERROR_CODE,NOTE_NOT_FOUND_ERROR_DESCRIPTION);
        }
    }

    public List<NoteDto> findByUserId(Long userId) {
        log.info("finding note by user id ...");
        List<Note> notes = noteRepository.findByUserId(userId);
        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<NoteDto> findAll() {
        log.info("find all notes ...");
        List<Note> notes = noteRepository.findAll();
        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    public NoteDto update(NoteDto noteDto) {
        log.info("Updating note {} ..",noteDto);
        Note existingNote = noteRepository.findById(noteDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Note not found with ID: " + noteDto.getId()));

        noteMapper.updateFromDto(noteDto, existingNote);

        Note updatedNote = noteRepository.save(existingNote);

        return noteMapper.toDto(updatedNote);
    }

    public void delete(Long noteId) {
        log.info("deleting user with id {}",noteId);
        if (!noteRepository.existsById(noteId)) {
            throw new EntityNotFoundException("Note not found with ID: " + noteId);
        }
        noteRepository.deleteById(noteId);
    }

    /*public List<NoteDto> findAllNotesForUser(Long userId) {
        List<Note> notes = noteRepository.findAllByUserId(userId);
        return noteMapper.toDtoList(notes);
    }*/
}
