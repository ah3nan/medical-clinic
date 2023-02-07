package com.ah3nan.medicalclinic.service;

import com.ah3nan.medicalclinic.dto.CreateUserRequestDTO;
import com.ah3nan.medicalclinic.dto.UserDTO;
import com.ah3nan.medicalclinic.error.exception.UsernameExistsException;
import com.ah3nan.medicalclinic.persistance.repository.UserRepository;
import com.ah3nan.medicalclinic.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    public UserDTO create(CreateUserRequestDTO createUserRequestDTO) {
        if (usernameExists(createUserRequestDTO.getUsername())) {
            log.debug("Username : {} Already Exists ",createUserRequestDTO.getUsername());
            throw new UsernameExistsException("Username Already Exists");
        }
        var user = userMapper.toEntity(createUserRequestDTO);
        var savedUser = userRepository.save(user);
        log.info("Created User : {}",savedUser);
        return userMapper.toDTO(savedUser);
    }
    public Optional<UserDTO> partialUpdate(UserDTO updatedUser) {
        log.debug("Update Existing User with Info : {} ",updatedUser);

        return userRepository
                .findById(updatedUser.getId())
                .map(existingUser -> {
                    if (updatedUser.getPassword() != null) {
                        existingUser.setPassword(updatedUser.getPassword());
                    }
                    if (updatedUser.getFirstName() != null) {
                        existingUser.setFirstName(updatedUser.getFirstName());
                    }
                    if (updatedUser.getLastName() != null) {
                        existingUser.setLastName(updatedUser.getLastName());
                    }
                    if (updatedUser.getPhoneNumber() != null) {
                        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
                    }
                    return existingUser;
                })
                .map(userRepository::save)
                .map(userMapper::toDTO);
    }
    public boolean usernameExists(String username) {
        return userRepository.findOneByUsername(username).isPresent();
    }

}
