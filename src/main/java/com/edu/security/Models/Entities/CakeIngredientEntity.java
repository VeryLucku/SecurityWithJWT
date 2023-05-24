package com.edu.security.Models.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "cake_types")
public class CakeIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cake_type_id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cake_id")
    private CakeEntity cake;

    @Enumerated(EnumType.STRING)
    private CakeIngredient type;

}
