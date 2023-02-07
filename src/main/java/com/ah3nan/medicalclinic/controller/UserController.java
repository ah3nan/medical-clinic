package com.ah3nan.medicalclinic.controller;

import com.ah3nan.medicalclinic.dto.CreateUserRequestDTO;
import com.ah3nan.medicalclinic.dto.UserDTO;
import com.ah3nan.medicalclinic.service.UserService;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Operation(summary = "Create New User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200" , description = "User Created",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "400" , description = "Validation Error",
                    content = {@Content(mediaType = "application/json")})
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO){
        var savedUser = userService.create(createUserRequestDTO);
        return ResponseEntity.ok(savedUser);
    }
}
