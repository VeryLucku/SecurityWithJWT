package com.edu.security.Mapper;

import com.edu.security.Models.DTO.CakeDTO;
import com.edu.security.Models.DTO.UserDTO;
import com.edu.security.Models.Entities.CakeEntity;
import com.edu.security.Models.Entities.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

public interface CakeMapper {
    CakeDTO map(CakeEntity cakeEntity);

    CakeEntity map(CakeDTO dto);
}
