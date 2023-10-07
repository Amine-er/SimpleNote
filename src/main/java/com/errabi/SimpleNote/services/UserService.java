package com.errabi.SimpleNote.services;


import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.repositories.UserRepository;
import com.errabi.SimpleNote.web.mapper.UserMapper;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    //Logger log = LoggerFactory.getLogger(UserService.class);

    // SLF4 specification
    // Impl : Log4j and Logback

    private final UserRepository userRepository ;
    private final UserMapper userMapper ;

    public void deleteById(Long id){
        log.info("deleting user with id {}",id);
        findById(id);
        userRepository.deleteById(id);
    }
    public UserDto save(UserDto dto){
        log.info("Saving user {} ..",dto);
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
          log.error("user not found for id  : "+id);
          throw new RuntimeException("User not found");
      }
    }
    public List<UserDto> findAll(){
        log.info("find all users ...");
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
       return users;
    }

    public UserDto findByUsername(String username){
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return userMapper.toDto(optionalUser.get());
        }else{
            throw new RuntimeException("User not found");
        }
    }

}