package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.Note;
import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.repositories.UserRepository;
import com.errabi.SimpleNote.web.mapper.UserMapper;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository ;
    private final UserMapper userMapper ;
    public UserDto save(UserDto dto){

        /*User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());


        List<Note> notes = new ArrayList<>() ;

        dto.getNotes().forEach(noteDto->{
            Note note = new Note();
            note.setTitle(noteDto.getTitle());
            note.setContent(noteDto.getContent());

            notes.add(note);
        });

        user.setNotes(notes); */
        User user = userMapper.toEntity(dto);
        userRepository.save(user);

       return dto ;
    }
    public UserDto findById(Long id){
      Optional<User> optionalUser =  userRepository.findById(id);
      if(optionalUser.isPresent()){
          UserDto dto = userMapper.toDto(optionalUser.get());
          return dto;
      }else{
          throw new RuntimeException("User not found");
      }
    }

}