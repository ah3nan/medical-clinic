package com.ah3nan.medicalclinic.service.mapper;

import com.ah3nan.medicalclinic.dto.CreateUserRequestDTO;
import com.ah3nan.medicalclinic.dto.UserDTO;
import com.ah3nan.medicalclinic.persistance.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    User toEntity(CreateUserRequestDTO createUserRequestDTO);

}
