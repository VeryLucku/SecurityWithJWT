package com.edu.security.Repository;

import com.edu.security.Models.Entities.CakeEntity;
import com.edu.security.Models.Entities.CakeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CakeRepository extends JpaRepository<CakeEntity, UUID> {

    @Query(value = """
        select c from cakes c left join cake_types t \s
        on t.cake.id = c.id \s
        where t.type = :type
    """)
    List<CakeEntity> getCakeEntitiesByIngredientType(CakeIngredient type);
}
