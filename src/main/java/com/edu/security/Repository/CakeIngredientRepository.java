package com.edu.security.Repository;

import com.edu.security.Models.Entities.CakeIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CakeIngredientRepository extends JpaRepository<CakeIngredientEntity, UUID> {
}
