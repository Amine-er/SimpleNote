package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.repositories.LabelRepository;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.web.mapper.LabelMapper;
import com.errabi.SimpleNote.web.model.LabelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final NoteRepository noteRepository;
    private final LabelMapper labelMapper;

    public LabelDto create(LabelDto dto){
        Label label = labelMapper.toEntity(dto);
        labelRepository.save(label);
        return dto;
    }

    public LabelDto findById(Long labelId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with ID: " + labelId));
        return labelMapper.toDto(label);
    }

    public List<LabelDto> findAll() {
        List<Label> labels = labelRepository.findAll();
        return labels.stream().map(labelMapper::toDto).collect(Collectors.toList());
    }

    public LabelDto update(LabelDto labelDto) {
        log.info("Updating note {} ..",labelDto);
        Label existingLabel = labelRepository.findById(labelDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Label not found with ID: " + labelDto.getId()));
        labelMapper.updateFromDto(labelDto, existingLabel);

        Label updatedLabel = labelRepository.save(existingLabel);
        return labelMapper.toDto(updatedLabel);
    }
    public void delete(Long labelId) {
        if(!labelRepository.existsById(labelId)) {
            throw new EntityNotFoundException("Label not found with ID: " + labelId);
        }
        labelRepository.deleteById(labelId);
    }

    public void addNoteToLabel(Long labelId, Long noteId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with ID: " + labelId));
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with ID: " + noteId));

        label.getNotes().add(note);

        note.getLabels().add(label);

        labelRepository.save(label);
        noteRepository.save(note);
    }
    public void removeNoteFromLabel(Long labelId, Long noteId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new EntityNotFoundException("Label not found with ID: " + labelId));
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with ID: " + noteId));

        label.getNotes().remove(note);
        note.getLabels().remove(label);

        labelRepository.save(label);
        noteRepository.save(note);
    }


}
