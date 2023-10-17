package com.errabi.simplenote.services;

import com.errabi.simplenote.entities.User;
import com.errabi.simplenote.exception.TechnicalException;
import com.errabi.simplenote.repositories.UserRepository;
import com.errabi.simplenote.web.mapper.UserMapper;
import com.errabi.simplenote.web.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import static com.errabi.simplenote.utils.SimpleNoteConst.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository ;
    private final UserMapper userMapper ;

    public void deleteById(Long id){
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
    public Page<UserDto> findAll(int page, int size){
       log.info("Finding all users with pagination ...");
        Pageable pageable = PageRequest.of(page, size);
        Page<User> usersPage = userRepository.findAll(pageable);
        if(usersPage.isEmpty()) {
            log.warn("No users found in the database.");
            return Page.empty();
        }
        return usersPage.map(userMapper::toDto);
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