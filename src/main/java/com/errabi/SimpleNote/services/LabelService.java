package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Label;
import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.repositories.LabelRepository;
import com.errabi.SimpleNote.repositories.NoteRepository;
import com.errabi.SimpleNote.web.mapper.LabelMapper;
import com.errabi.SimpleNote.web.mapper.NoteMapper;
import com.errabi.SimpleNote.web.model.LabelDto;
import com.errabi.SimpleNote.web.model.NoteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    public LabelDto save(LabelDto dto){

        Label label = labelMapper.toEntity(dto);
        labelRepository.save(label);

        return dto ;
    }
}
