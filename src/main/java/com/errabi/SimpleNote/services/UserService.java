package com.errabi.SimpleNote.services;

import com.errabi.SimpleNote.entities.User;
import com.errabi.SimpleNote.exception.TechnicalException;
import com.errabi.SimpleNote.repositories.UserRepository;
import com.errabi.SimpleNote.web.mapper.UserMapper;
import com.errabi.SimpleNote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static com.errabi.SimpleNote.utils.SimpleNoteConst.*;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
        /*log.info("Deleting user with id {}",id);
        if (!userRepository.existsById(id)) {
            log.error("Failed to delete user. User not found with ID: {}", id);
            throw new TechnicalException(
                    USER_NOT_FOUND_ERROR_CODE,
                    USER_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND);
        }*/
        findById(id);
        userRepository.deleteById(id);
    }
    public UserDto save(UserDto dto){
        log.info("Saving user {} ..",dto);
        // Check if the username already exists
        if (userRepository.existsByUsername(dto.getUsername())) {
            log.error("Failed to save user. Username {} already exists.", dto.getUsername());
            throw new TechnicalException(
                    USER_ALREADY_EXISTS_ERROR_CODE,
                    USER_ALREADY_EXISTS_ERROR_DESCRIPTION,
                    STATUS_CONFLICT);
        }
        User user = userMapper.toEntity(dto);
        userRepository.save(user);
       return dto ;
    }
    public UserDto findById(Long id){
      Optional<User> optionalUser =  userRepository.findById(id);
      if(optionalUser.isPresent()){
          log.info("Finding user with id {}",id);
          return userMapper.toDto(optionalUser.get());
      }else{
          log.error("User not found with id {}", id);
          throw new TechnicalException(
                  USER_NOT_FOUND_ERROR_CODE,
                  USER_NOT_FOUND_ERROR_DESCRIPTION,
                  STATUS_NOT_FOUND);
      }
    }
    public List<UserDto> findAll(){
       log.info("Finding all users ...");
       List<UserDto> users = new ArrayList<>();
       userRepository.findAll().forEach(element->{
           users.add(userMapper.toDto(element));
       });

       if (users.isEmpty()) {
           log.warn("No users found in the database.");
           return Collections.emptyList();
       }
       /*
       for loop
       List<User> list = userRepository.findAll() ;
       for(int i = 0;i<list.size();i++){
           users.add(userMapper.toDto(list.get(i)));
       }

       java 8 stream functional programming and lambda expression OCP
       List<UserDto> userDtos =   userRepository.findAll().stream()
                                                          .map(e->userMapper.toDto(e))
                                                          .collect(Collectors.toList());
       */
       return users;
    }
    public UserDto findByUsername(String username){
        log.info("Finding user with username {}",username);
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isPresent()){
            return userMapper.toDto(optionalUser.get());
        }else{
            log.error("User not found with username {}", username);
            throw new TechnicalException(
                    USER_NOT_FOUND_ERROR_CODE,
                    USER_NOT_FOUND_ERROR_DESCRIPTION,
                    STATUS_NOT_FOUND
            );
        }
    }
    public UserDto updateUser(UserDto userDto) {
        log.info("Updating user {} ..",userDto);
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> {
                    log.error("User not found with ID {}", userDto.getId());
                    return new TechnicalException(
                            USER_NOT_FOUND_ERROR_CODE,
                            USER_NOT_FOUND_ERROR_DESCRIPTION,
                            STATUS_NOT_FOUND

                    );
                });
        userMapper.updateFromDto(userDto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }
}