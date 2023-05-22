package com.edu.security.Mapper;

import com.edu.security.Models.DTO.UserDTO;
import com.edu.security.Models.Entities.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDTO dto);
}
