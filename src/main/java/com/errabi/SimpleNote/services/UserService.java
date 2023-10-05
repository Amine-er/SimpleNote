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
        // check if the username already exist
        // throw exception if the user already exist
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
    public List<UserDto> findAll(){
        List<UserDto> users = new ArrayList<>();
       userRepository.findAll().forEach(element->{
           users.add(userMapper.toDto(element));
       });
       // for loop
       /*List<User> list = userRepository.findAll() ;
       for(int i = 0;i<list.size();i++){
           users.add(userMapper.toDto(list.get(i)));
       }*/
       // java 8 stream functional programming and lambda expression OCP
      /*List<UserDto> userDtos =   userRepository.findAll().stream()
                                                          .map(e->userMapper.toDto(e))
                                                          .collect(Collectors.toList());*/
       return users ;
    }
}