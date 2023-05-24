package com.edu.security.Service;

import com.edu.security.Models.Entities.CakeEntity;
import com.edu.security.Repository.CakeRepository;
import com.edu.security.Repository.CakeIngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CakeService {

    private final CakeRepository cakeRepository;
    private final CakeIngredientRepository cakeIngredientRepository;

    @Transactional
    public CakeEntity save(CakeEntity entity) {
        cakeIngredientRepository.saveAll(entity.getTypes());
        return cakeRepository.save(entity);
    }

    public CakeEntity getCakeById(UUID id) {
        CakeEntity cakeEntity = cakeRepository.getReferenceById(id);
        return cakeEntity;
    }
}
