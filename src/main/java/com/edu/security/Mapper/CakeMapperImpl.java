package com.edu.security.Mapper;

import com.edu.security.Models.DTO.CakeDTO;
import com.edu.security.Models.Entities.CakeEntity;
import com.edu.security.Models.Entities.CakeIngredientEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CakeMapperImpl implements CakeMapper{

    @Override
    public CakeDTO map(CakeEntity cakeEntity) {
        return CakeDTO.builder()
                .id(cakeEntity.getId())
                .cost(cakeEntity.getCost())
                .name(cakeEntity.getName())
                .types(cakeEntity.getTypes().
                        stream().map(CakeIngredientEntity::getType)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public CakeEntity map(CakeDTO dto) {
        CakeEntity cakeEntity = CakeEntity.builder()
                .name(dto.getName())
                .cost(dto.getCost())
                .build();
        var list = dto.getTypes()
                        .stream().map(type -> CakeIngredientEntity.builder()
                        .cake(cakeEntity)
                        .type(type)
                        .build())
                        .toList();
        cakeEntity.setTypes(list);
        return cakeEntity;
    }
}
