package com.ah3nan.medicalclinic;

import com.ah3nan.medicalclinic.persistance.entity.User;
import com.ah3nan.medicalclinic.persistance.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void findOneByUsername() {
        var user = new User();
        user.setUsername("mido");
        user.setPassword("ahmed545345");
        user.setFirstName("Mido");
        user.setLastName("Metwally");
        user.setPhoneNumber("011166585");
        user.setRole("ROLE_ADMIN");
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        Optional<User> userOptional = userRepository.findOneByUsername(user.getUsername());
        assertThat(userOptional)
                .isPresent()
                .hasValueSatisfying(existingUser -> {
                    assertEquals(existingUser.getUsername(), user.getUsername());
                    assertEquals(existingUser.getPassword(), user.getPassword());
                    assertEquals(existingUser.getFirstName(), user.getFirstName());
                    assertEquals(existingUser.getLastName(), user.getLastName());
                    assertEquals(existingUser.getPhoneNumber(), user.getPhoneNumber());
                    assertEquals(existingUser.getRole(), user.getRole());
                });

    }
}
