package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.exception.TechnicalException;
import com.errabi.SimpleNote.repositories.LabelRepository;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.web.mapper.LabelMapper;
import com.errabi.SimpleNote.web.model.LabelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static com.errabi.SimpleNote.utils.SimpleNoteConst.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final NoteRepository noteRepository;
    private final LabelMapper labelMapper;

    public LabelDto create(LabelDto dto){
        log.info("Creating label {} ..", dto);
        try {
            Label label = labelMapper.toEntity(dto);
            labelRepository.save(label);
        } catch(DataIntegrityViolationException ex) {
            log.error("Error occurred while saving the label: Data Integrity Violation", ex);
            throw new TechnicalException(
                    LABEL_SAVE_ERROR_CODE,
                    LABEL_SAVE_ERROR_DESCRIPTION,
                    STATUS_BAD_REQUEST
            );
        } catch(Exception ex) {
            log.error("Unexpected error occurred while saving the label", ex);
            throw new TechnicalException(
                    LABEL_UNEXPECTED_ERROR_CODE,
                    LABEL_UNEXPECTED_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
        return dto;
    }
    public LabelDto findById(Long labelId) {
        Optional<Label> optionalLabel =  labelRepository.findById(labelId);
        if(optionalLabel.isPresent()){
            log.info("Finding label with id {}",labelId);
            return labelMapper.toDto(optionalLabel.get());
        }else{
            log.error("Label not found with id {}", labelId);
            throw new TechnicalException(
                    LABEL_NOT_FOUND_ERROR_CODE,
                    LABEL_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }
    }
    public List<LabelDto> findAll() {
        log.info("Fetching all labels...");
        List<Label> labels;
        try {
            labels = labelRepository.findAll();
        } catch(Exception ex) {
            log.error("Unexpected error occurred while fetching all labels", ex);
            throw new TechnicalException(
                    LABEL_FETCH_ALL_ERROR_CODE,
                    LABEL_FETCH_ALL_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
        if(labels.isEmpty()) {
            log.warn("No labels found in the system.");
            throw new TechnicalException(
                    LABELS_NOT_AVAILABLE_ERROR_CODE,
                    LABELS_NOT_AVAILABLE_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND
            );
        }
        return labels.stream().map(labelMapper::toDto).collect(Collectors.toList());
    }
    public LabelDto update(LabelDto labelDto) {
        log.info("Updating label {} ..", labelDto);
        Label existingLabel = labelRepository.findById(labelDto.getId()).orElse(null);
        if (existingLabel == null) {
            log.error("Label not found with ID {}", labelDto.getId());
            throw new TechnicalException(
                    LABEL_NOT_FOUND_ERROR_CODE,
                    LABEL_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND
            );
        }
        try {
            labelMapper.updateFromDto(labelDto, existingLabel);
            Label updatedLabel = labelRepository.save(existingLabel);
            return labelMapper.toDto(updatedLabel);
        } catch(Exception ex) {
            log.error("Unexpected error occurred while updating label with ID {}", labelDto.getId(), ex);
            throw new TechnicalException(
                    LABEL_UPDATE_ERROR_CODE,
                    LABEL_UPDATE_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
    }
    public void delete(Long labelId) {
        log.info("Deleting label with ID {}", labelId);
        if (!labelRepository.existsById(labelId)) {
            log.error("Label not found with ID {}", labelId);
            throw new TechnicalException(
                    LABEL_NOT_FOUND_ERROR_CODE,
                    LABEL_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND
            );
        }
        try {
            labelRepository.deleteById(labelId);
        } catch (Exception ex) {
            log.error("Unexpected error occurred while deleting label with ID {}", labelId, ex);
            throw new TechnicalException(
                    LABEL_DELETE_ERROR_CODE,
                    LABEL_DELETE_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
    }
    public void addNoteToLabel(Long labelId, Long noteId) {
        log.info("Adding note with ID {} to label with ID {}", noteId, labelId);
        Label label;
        label = labelRepository.findById(labelId)
                .orElseThrow(() -> {
                    log.error("Label not found with ID {}", labelId);
                    return new TechnicalException(
                            LABEL_NOT_FOUND_ERROR_CODE,
                            LABEL_NOT_FOUND_ERROR_DESCRIPTION,
                            STATUS_NOT_FOUND
                    );
                });
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> {
                    log.error("Note not found with ID {}", noteId);
                    return new TechnicalException(
                            NOTE_NOT_FOUND_ERROR_CODE,
                            NOTE_NOT_FOUND_ERROR_DESCRIPTION,
                            STATUS_NOT_FOUND
                    );
                });
        label.getNotes().add(note);
        note.getLabels().add(label);
        try {
            labelRepository.save(label);
            noteRepository.save(note);
        } catch (Exception ex) {
            log.error("Unexpected error occurred while associating note with ID {} to label with ID {}", noteId, labelId, ex);
            throw new TechnicalException(
                    LABEL_NOTE_ASSOCIATION_ERROR_CODE,
                    LABEL_NOTE_ASSOCIATION_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
    }
    public void removeNoteFromLabel(Long labelId, Long noteId) {
        log.info("Removing note with ID {} from label with ID {}", noteId, labelId);
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> {
                    log.error("Label not found with ID {}", labelId);
                    return new TechnicalException(
                            LABEL_NOT_FOUND_ERROR_CODE,
                            LABEL_NOT_FOUND_ERROR_DESCRIPTION,
                            STATUS_NOT_FOUND
                    );
                });
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> {
                    log.error("Note not found with ID {}", noteId);
                    return new TechnicalException(
                            NOTE_NOT_FOUND_ERROR_CODE,
                            NOTE_NOT_FOUND_ERROR_DESCRIPTION,
                            STATUS_NOT_FOUND
                    );
                });
        if(!label.getNotes().contains(note) || !note.getLabels().contains(label)) {
            log.error("Note with ID {} is not associated with Label with ID {}", noteId, labelId);
            throw new TechnicalException(
                    NOTE_NOT_ASSOCIATED_WITH_LABEL_ERROR_CODE,
                    NOTE_NOT_ASSOCIATED_WITH_LABEL_ERROR_DESCRIPTION,
                    STATUS_BAD_REQUEST
            );
        }
        label.getNotes().remove(note);
        note.getLabels().remove(label);
        try {
            labelRepository.save(label);
            noteRepository.save(note);
        } catch (Exception ex) {
            log.error("Unexpected error occurred while dissociating note with ID {} from label with ID {}", noteId, labelId, ex);
            throw new TechnicalException(
                    LABEL_NOTE_DISSOCIATION_ERROR_CODE,
                    LABEL_NOTE_DISSOCIATION_ERROR_DESCRIPTION,
                    STATUS_INTERNAL_SERVER_ERROR
            );
        }
    }
}
