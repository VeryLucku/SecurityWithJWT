package com.edu.security.Mapper;

import com.edu.security.Models.DTO.CakeDTO;
import com.edu.security.Models.Entities.CakeEntity;

public interface CakeMapper {
    CakeDTO map(CakeEntity cakeEntity);

    CakeEntity map(CakeDTO dto);
}
