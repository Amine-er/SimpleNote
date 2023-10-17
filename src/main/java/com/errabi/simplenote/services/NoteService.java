package com.errabi.simplenote.services;

import com.errabi.simplenote.entities.Note;
import com.errabi.simplenote.entities.User;
import com.errabi.simplenote.exception.TechnicalException;
import com.errabi.simplenote.repositories.NoteRepository;
import com.errabi.simplenote.repositories.UserRepository;
import com.errabi.simplenote.web.mapper.NoteMapper;
import com.errabi.simplenote.web.model.NoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.errabi.simplenote.utils.SimpleNoteConst.*;


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
    public Page<NoteDto> findAll(int page, int size) {
        log.info("Finding all notes ...");
        Pageable pageable = PageRequest.of(page, size);
        Page<Note> notesPage = noteRepository.findAll(pageable);
        if(notesPage.isEmpty()) {
            log.warn("No notes found in the database.");
            return Page.empty();
        }
        return notesPage.map(noteMapper::toDto);
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
}
