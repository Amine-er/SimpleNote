package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.exception.TechnicalException;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.repositories.UserRepository;
import com.errabi.SimpleNote.web.mapper.NoteMapper;
import com.errabi.SimpleNote.web.model.NoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.errabi.SimpleNote.utils.SimpleNoteConst.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;

    public NoteDto create(NoteDto dto, Long userId){
        log.info("Saving note {} ..",dto);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            log.error("Failed to save note. User not found with ID: {}", userId);
            throw new TechnicalException(
                    USER_NOT_FOUND_ERROR_CODE,
                    USER_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }
        Note note = noteMapper.toEntity(dto);
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }
    public NoteDto findById(Long id){
        log.info("finding note by id ...");
        Optional<Note> optionalNote =  noteRepository.findById(id);
        if(optionalNote.isPresent()){
            return noteMapper.toDto(optionalNote.get());
        }else{
            log.error("Note not found with id {}",id);
            throw new TechnicalException(
                    NOTE_NOT_FOUND_ERROR_CODE,
                    NOTE_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }
    }
    public List<NoteDto> findByUserId(Long userId) {
        log.info("Finding notes for user id: {}", userId);

        if (!userRepository.existsById(userId)) {
            log.error("Failed to find notes. User not found with ID: {}", userId);
            throw new TechnicalException(
                    USER_NOT_FOUND_ERROR_CODE,
                    USER_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }

        List<Note> notes = noteRepository.findByUserId(userId);
        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<NoteDto> findAll() {
        log.info("Finding all notes ...");
        List<Note> notes = noteRepository.findAll();

        if (notes.isEmpty()) {
            log.warn("No notes found in the database.");
            return Collections.emptyList();
        }
        return notes.stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }
    public NoteDto update(NoteDto noteDto) {
        log.info("Updating note {} ..", noteDto);

        Note existingNote = noteRepository.findById(noteDto.getId()).orElse(null);

        if (existingNote == null) {
            log.error("Failed to update note. Note not found with ID: {}", noteDto.getId());
            throw new TechnicalException(
                    NOTE_NOT_FOUND_ERROR_CODE,
                    NOTE_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }

        noteMapper.updateFromDto(noteDto, existingNote);
        Note updatedNote = noteRepository.save(existingNote);
        return noteMapper.toDto(updatedNote);
    }
    public void delete(Long noteId) {
        log.info("Deleting note with id {}", noteId);

        if (!noteRepository.existsById(noteId)) {
            log.error("Failed to delete note. Note not found with ID: {}", noteId);
            throw new TechnicalException(
                    NOTE_NOT_FOUND_ERROR_CODE,
                    NOTE_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }
        noteRepository.deleteById(noteId);
    }
    /*public List<NoteDto> findAllNotesForUser(Long userId) {
        List<Note> notes = noteRepository.findAllByUserId(userId);
        return noteMapper.toDtoList(notes);
    }*/
}
