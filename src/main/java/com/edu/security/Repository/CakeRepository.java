package com.edu.security.Repository;

import com.edu.security.Models.Entities.CakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CakeRepository extends JpaRepository<CakeEntity, UUID> {
}
