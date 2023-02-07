package com.ah3nan.medicalclinic;

import com.ah3nan.medicalclinic.persistance.entity.User;
import com.ah3nan.medicalclinic.persistance.repository.UserRepository;
import com.ah3nan.medicalclinic.service.UserService;
import com.ah3nan.medicalclinic.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class UserServiceTests {
    UserMapper USER_INSTANCE = Mappers.getMapper( UserMapper.class );

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository,USER_INSTANCE);
    }
    @Test
    public void findByUsernameTest(){
        var user = new User();
        user.setUsername("mido");
        user.setPassword("ahmed545345");
        user.setFirstName("Mido");
        user.setLastName("Metwally");
        user.setPhoneNumber("011166585");
        user.setRole("ROLE_ADMIN");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        Optional<User> userOptional = Optional.of(user);
        Mockito.when(userRepository.findOneByUsername(Mockito.anyString())).thenReturn(userOptional);
        boolean userServiceOptional = userService.usernameExists("mido");
        assertTrue(userServiceOptional);
    }
}
